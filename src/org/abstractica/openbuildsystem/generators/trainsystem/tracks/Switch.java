package org.abstractica.openbuildsystem.generators.trainsystem.tracks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;

public class Switch
{
	private final double trackGauge = 80;
	private final double totalLength = 480;
	private final double sleeperLength = 120;
	private final double sleeperWidth = 20;
	private final int angularResolution = 1024;
	private final int numberOfSleepers = 7;
	private final int turnSleeper = 2;
	private final int crossBegin = 3;
	private final int crossEnd = 4;
	private final CSG csg;
	private final Construct3D c3d;
	private final Print3DAdjust adjust;
	private final TrackBuilder trackBuilder;
	private final ParallelSwitchTrackBuilder sTrackBuilder;
	private final ClickSystem clickSystem;

	public Switch(CSG csg, Print3DAdjust adjust)
	{

		this.csg = csg;
		this.c3d = csg.csg3D().construct3D();
		this.adjust = adjust;
		this.trackBuilder = new TrackBuilder(csg, adjust, 5, 10, 2, 1);
		this.sTrackBuilder = new ParallelSwitchTrackBuilder(csg, adjust, trackBuilder,
				trackGauge, totalLength, sleeperLength, true, angularResolution);
		this.clickSystem = new ClickSystemImpl(csg, adjust, 5);
	}

	public Module3D switchLayout()
	{
		Module3DFrom3D layout = c3d.union3D();

		//Left straight
		layout.add(c3d.translate3D(-0.5*trackGauge, 0, 5)
				.add(trackBuilder.straightTrack(totalLength, true)));

		//Right straight
		layout.add(c3d.translate3D(0.5*trackGauge, 0, 5)
				.add(trackBuilder.straightTrack(totalLength, true)));

		//Left curve
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.curveTrackSection(0, 1, true)));

		//Right curve
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.curveTrackSection(0, 1, false)));

		return layout;
	}

	public Module3D crossSection()
	{
		Module3DFrom3D tracks = c3d.union3D();
		Module3DFrom3D trackCutout = c3d.difference3D().add(tracks);

		//Tracks
		double fBegin = getF(crossBegin);
		double fEnd = getF(crossEnd);
		tracks.add(straightTrack(fBegin, fEnd, true,false, false));
		tracks.add(curveTrack(fBegin, fEnd,false, false, false));

		Module3D curveCut = curveTrackCutout(fBegin, fEnd, false, true);
		curveCut = c3d.translate3D(0,0,10).add(curveCut);
		trackCutout.add(curveCut);
		curveCut = curveTrackCutout(fBegin, fEnd, false, false);
		curveCut = c3d.translate3D(0,0,15).add(curveCut);
		trackCutout.add(curveCut);

		Module3D straightCut = straightTrackCutout(fBegin, fEnd, true, true);
		straightCut = c3d.translate3D(0,0,10).add(straightCut);
		trackCutout.add(straightCut);

		Module3DFrom3D cross = c3d.union3D().add(trackCutout);

		//Mounts
		cross.add(curveTransform(fBegin, trackMount(true, false, false, 0)));
		cross.add(curveTransform(fEnd, trackMount(false, true, false, 0)));
		cross.add(straightTransform(fBegin, trackMount(true, true, true, 0)));
		cross.add(straightTransform(fEnd, trackMount(false, false, true, 0)));

		return cross;
	}

	public Module3D curveSwitchTrack()
	{
		double fBegin = 0;
		double fEnd = getF(turnSleeper);
		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D cutout = c3d.difference3D().add(solid);
		Module3D track = curveTrack(
				fBegin + mmToFouterCurve(15),
				fEnd + mmToFouterCurve(adjust.solidRoundLoose().xy()),
				false,
				true,
				false);
		solid.add(track);

		cutout.add(straightTrackCutout(fBegin, fEnd, false, false));

		//Turn hole
		Module3D turnMount = roundTrackMountBase(false, true, false, adjust.solidRoundLoose().xy());
		solid.add(curveTransform(fEnd, turnMount));
		Module3D turnClickHole = roundClickHole(false, true, false);
		turnClickHole = c3d.translate3D(0,0,5).add(turnClickHole);
		cutout.add(curveTransform(fEnd, turnClickHole));

		//Actuator hole
		double fActuator = 0.5*(getF(0) + getF(1));
		Module3D actuatorMount = roundTrackMountBase(false, true, false, 0);
		solid.add(curveTransform(fActuator, actuatorMount));
		Module3D actuatorClickHole = roundClickHole(false, true, false);
		actuatorClickHole = c3d.translate3D(0,0,5).add(actuatorClickHole);
		cutout.add(curveTransform(fActuator, actuatorClickHole));

		return cutout;
	}

	public Module3D straightSwitchTrack()
	{
		double fBegin = 0;
		double fEnd = getF(turnSleeper);
		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D cutout = c3d.difference3D().add(solid);
		Module3D track = straightTrack(
				fBegin + mmToFStraight(15),
				fEnd + mmToFStraight(adjust.solidRoundLoose().xy()),
				true,
				true,
				false);
		solid.add(track);

		cutout.add(curveTrackCutout(fBegin, fEnd, true, false));

		//Turn hole
		Module3D turnMount = roundTrackMountBase(false, false, true, adjust.solidRoundLoose().xy());
		solid.add(straightTransform(fEnd, turnMount));
		Module3D turnClickHole = roundClickHole(false, false, true);
		turnClickHole = c3d.translate3D(0,0,5).add(turnClickHole);
		cutout.add(straightTransform(fEnd, turnClickHole));

		//Actuator hole
		double fActuator = 0.5*(getF(0) + getF(1));
		Module3D actuatorMount = roundTrackMountBase(false, false, true, 0);
		solid.add(straightTransform(fActuator, actuatorMount));
		Module3D actuatorClickHole = roundClickHole(false, false, true);
		actuatorClickHole = c3d.translate3D(0,0,5).add(actuatorClickHole);
		cutout.add(straightTransform(fActuator, actuatorClickHole));

		return cutout;
	}

	public Module3D leftCurve02()
	{
		Module3DFrom3D res = c3d.union3D();
		double fBegin = getF(0);
		double fMiddle = getF(1);
		double fEnd = getF(2);
		Module3D track = curveTrack(fBegin, fEnd, true, false, false);
		res.add(track);

		//Mounts
		//Begin
		res.add(curveTransform(fBegin, trackMount(true, true, true, 0)));
		res.add(curveTransform(fBegin, trackMount(true, false, true, 0)));
		//Middle outside
		res.add(curveTransform(fMiddle, trackMount(true, true, true, 0)));
		res.add(curveTransform(fMiddle, trackMount(false, true, true, 0)));
		//End
		res.add(curveTransform(fEnd, trackMount(false, true, true, 0)));
		res.add(curveTransform(fEnd, trackMount(false, false, true, 0)));
		return res;
	}

	public Module3D rightStraight02()
	{
		Module3DFrom3D res = c3d.union3D();
		double fBegin = getF(0);
		double fMiddle = getF(1);
		double fEnd = getF(2);
		Module3D track = straightTrack(fBegin, fEnd, false, false, false);
		res.add(track);

		//Mounts
		//Begin
		res.add(straightTransform(fBegin, trackMount(true, true, false, 0)));
		res.add(straightTransform(fBegin, trackMount(true, false, false, 0)));
		//Middle outside
		res.add(straightTransform(fMiddle, trackMount(true, false, false, 0)));
		res.add(straightTransform(fMiddle, trackMount(false, false, false, 0)));
		//End
		res.add(straightTransform(fEnd, trackMount(false, true, false, 0)));
		res.add(straightTransform(fEnd, trackMount(false, false, false, 0)));
		return res;
	}

	public Module3D allSleepers()
	{
		Module3DFrom3D union = c3d.union3D();
		for(int i = 1; i < numberOfSleepers - 1; ++i)
		{
			union.add(doubleSleeper(i));
		}
		return union;
	}

	public Module3D doubleSleeper(int n)
	{
		double f = getF(n);
		Module3D base = csg.csg3D().shapes3D().box3D(sleeperLength, sleeperWidth, 5);
		base = c3d.translate3D(0,0,2.5).add(base);
		Module3D left = curveTransform(f, base);
		Module3D right = straightTransform(f, base);
		Module3DFrom3D solid = c3d.union3D().add(left).add(right);
		Module3DFrom3D diff = c3d.difference3D().add(solid);
		int columns = 6;
		if(n == turnSleeper)
		{
			columns = 4;
			diff.add(straightTransform(f, roundClickHole(false, false, true)));
			diff.add(curveTransform(f, roundClickHole(false, true, false)));
			diff.add(straightTransform(f, clickHole(true, true, true)));
			diff.add(curveTransform(f, clickHole(true, false, false)));
			diff.add(curveTransform(f, clickerHolesLeft(3)));
			diff.add(straightTransform(f, clickerHolesRight(3)));
			if(n != crossBegin)
			{
				diff.add(straightTransform(f, clickHole(true, false, true)));
				diff.add(curveTransform(f, clickHole(true, true, false)));
			}
			//solid.add(curveTransform(f, roundMount(false, true, false, adjust.solidRoundLoose().xy())));
		}
		else if(n == crossBegin)
		{
			columns = 7;
			diff.add(curveTransform(f, clickHole(true, false, false)));
			diff.add(straightTransform(f, clickHole(true, true, true)));
			diff.add(straightTransform(f, clickHole(false, true, true)));
			diff.add(straightTransform(f, clickHole(false, false, true)));
			diff.add(curveTransform(f, clickHole(false, true, false)));
			diff.add(curveTransform(f, clickHole(false, false, false)));
		}
		else if(n == crossEnd)
		{
			columns = 10;
			diff.add(straightTransform(f, clickHole(true, true, true)));
			diff.add(curveTransform(f, clickHole(true, false, false)));
		}
		if(n > crossEnd)
		{
			columns = 11;
		}
		diff.add(curveTransform(f, clickerHolesLeft(columns)));
		diff.add(straightTransform(f, clickerHolesRight(columns)));
		return diff;
	}

	private Module3D clickerHolesLeft(int columns)
	{
		Module3DFrom3D res = c3d.union3D();
		for (int x = 0; x < columns; ++x)
		{
			double posX = -10 + x*10;
			res.add(c3d.translate3D(posX, 0, 0).add(clickHole(true, true, true)));
			res.add(c3d.translate3D(posX, 0, 0).add(clickHole(false, true, true)));
		}
		return res;
	}

	private Module3D clickerHolesRight(int columns)
	{
		Module3D clickerHole = clickSystem.clickerCutout(5);
		Module3DFrom3D res = c3d.union3D();
		for(int y = 0; y < 2; ++y)
		{
			for (int x = 0; x < columns; ++x)
			{
				double posY = -5 + y*10;
				double posX = 0.5*sleeperLength - 5 - x*10;
				res.add(c3d.translate3D(posX, posY, 0).add(clickerHole));
			}
		}
		return res;
	}

	private Module3D clickHole(boolean north, boolean west, boolean left)
	{
		Module3D clickHole = clickSystem.clickerCutout(5);
		double a = left ? -0.5 : 0.5;
		double b = west ? -5 : 5;
		double c = north ? 5 : -5;
		clickHole = c3d.translate3D(a*trackGauge+b, c, 0).add(clickHole);
		return clickHole;
	}

	private Module3D roundClickHole(boolean north, boolean west, boolean left)
	{
		double a = left ? -0.5 : 0.5;
		double b = west ? -7.5 : 7.5;
		double c = north ? 5 : -5;
		Module3D clickHole = clickSystem.roundClickerCutout(5);
		clickHole = c3d.translate3D(a*trackGauge+b, c, 0).add(clickHole);
		return clickHole;
	}

	private Module3D trackMount(boolean north, boolean west, boolean left, double adj)
	{
		double a = left ? -0.5 : 0.5;
		double b = west ? -5 : 5;
		double c = north ? 5 : -5;
		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D diff = c3d.difference3D().add(solid);
		Module3D box = csg.csg3D().shapes3D().box3D(10+2*adj, 10+2*adj, 5);
		box = c3d.translate3D(a*trackGauge+b,c,7.5).add(box);
		solid.add(box);
		Module3D clickHole = clickSystem.clickerCutout(5);
		clickHole = c3d.translate3D(a*trackGauge+b, c, 5).add(clickHole);
		diff.add(clickHole);
		return diff;
	}

	private Module3D roundTrackMountBase(boolean north, boolean west, boolean left, double adj)
	{
		double a = left ? -0.5 : 0.5;
		double b = west ? -7.5 : 7.5;
		double c = north ? 5 : -5;
		double d = west ? -3.5 : 3.5;
		Module3DFrom3D solid = c3d.union3D();
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(10+2*adj, 5, 64);
		cyl = c3d.translate3D(a*trackGauge+b,c,7.5).add(cyl);
		solid.add(cyl);
		Module3D box = csg.csg3D().shapes3D().box3D(8, 10+2*adj, 5);
		box = c3d.translate3D(a*trackGauge+d, c, 7.5).add(box);
		solid.add(box);
		return solid;
	}

	private Module3D roundTrackMount(boolean north, boolean west, boolean left, double adj)
	{
		Module3DFrom3D res = c3d.difference3D().add(roundTrackMountBase(north, west, left, adj));
		double a = left ? -0.5 : 0.5;
		double b = west ? -10 : 10;
		double c = north ? 5 : -5;
		Module3D clickHole = clickSystem.roundClickerCutout(5);
		clickHole = c3d.translate3D(a*trackGauge+b, c, 5).add(clickHole);
		res.add(clickHole);
		return res;
	}

	private Module3D curveTransform(double f, Module3D child)
	{
		return sTrackBuilder.curveTransform(f, child);
	}

	private Module3D straightTransform(double f, Module3D child)
	{
		return sTrackBuilder.straightTransform(f, child);
	}

	private Module3D straightTrack(double fBegin, double fEnd, boolean left,
	                               boolean insideFoot, boolean outsideFoot)
	{
		Module3D track = sTrackBuilder.straightTrackSection(fBegin,fEnd,left,insideFoot, outsideFoot);
		return c3d.translate3D(0,0,5).add(track);
	}

	private Module3D curveTrack(double fBegin, double fEnd, boolean left,
	                            boolean insideFoot, boolean outsideFoot)
	{
		Module3D track = sTrackBuilder.curveTrackSection(fBegin,fEnd,left,insideFoot, outsideFoot);
		return c3d.translate3D(0,0,5).add(track);
	}

	private double getF(int sleeperNumber)
	{
		return ((double) sleeperNumber) / (numberOfSleepers - 1);
	}

	private TrackBuilder cutTrackBuilder()
	{
		return new TrackBuilder(csg, adjust, 5.002, 20, 2, 1);
	}

	private ParallelSwitchTrackBuilder cutTrackBuilder(boolean insideTrack)
	{
		TrackBuilder tb = cutTrackBuilder();
		double gauge = trackGauge;
		if(insideTrack)
		{
			gauge-=10;
		}
		return new ParallelSwitchTrackBuilder(  csg, adjust, tb, gauge, totalLength,
												sleeperLength, false, angularResolution);
	}

	private Module3D curveTrackCutout(double fBegin, double fEnd, boolean left, boolean insideTrack)
	{
		ParallelSwitchTrackBuilder cutBuilder = cutTrackBuilder(insideTrack);
		return cutBuilder.curveTrackSection(fBegin, fEnd, left);
	}

	private Module3D straightTrackCutout(double fBegin, double fEnd, boolean left, boolean insideTrack)
	{
		double gauge = insideTrack ? trackGauge - 10 : trackGauge;
		double tx = left ? -0.5*gauge : 0.5*gauge;
		double mmBegin = fBegin * totalLength;
		double mmEnd = fEnd * totalLength;
		double mmLength = mmEnd - mmBegin;
		Module3D cutout = cutTrackBuilder().straightTrack(mmLength, false);
		cutout = c3d.translate3D(tx, mmBegin, 0).add(cutout);
		return cutout;
	}

	private double mmToFStraight(double mm)
	{
		return sTrackBuilder.mmToFStraight(mm);
	}

	private double mmToFInnerCurve(double mm)
	{
		return sTrackBuilder.mmToFInnerCurve(mm);
	}

	private double mmToFouterCurve(double mm)
	{
		return sTrackBuilder.mmToFouterCurve(mm);
	}
}
