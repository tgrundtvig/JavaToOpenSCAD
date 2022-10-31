package org.abstractica.openbuildsystem.generators.trainsystem.tracks.switches;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;
import org.abstractica.openbuildsystem.generators.trainsystem.tracks.TrackBuilder;

public class Switch
{
	private final double trackGauge = 80;
	private final double totalLength = 600;
	private final double xTranslate = 120;
	private final double sleeperLength = 120;
	private final double sleeperWidth = 30;
	private final int angularResolution = 1024;
	private final int numberOfSleepers = 7;
	private final int turnSleeper = 2;
	private final int crossBegin = 2;
	private final int crossEnd = 4;
	private final CSG csg;
	private final Construct3D c3d;
	private final Print3DAdjust adjust;
	private final ParallelSwitchTrackBuilder sTrackBuilder;
	private final ClickSystem clickSystem;

	public Switch(CSG csg, Print3DAdjust adjust)
	{

		this.csg = csg;
		this.c3d = csg.csg3D().construct3D();
		this.adjust = adjust;
		TrackBuilder trackBuilder = new TrackBuilder(csg, adjust, 5, 10, 2, 1);
		this.sTrackBuilder = new ParallelSwitchTrackBuilder(csg, adjust, trackBuilder,
				trackGauge, totalLength, xTranslate, true, angularResolution);
		this.clickSystem = new ClickSystemImpl(csg, adjust, 5);
	}

	public Module3D switchLayout()
	{
		Module3DFrom3D layout = c3d.union3D();

		//Left straight
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.trackSection(0, 1, false, true)));

		//Right straight
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.trackSection(0, 1, false, false)));

		//Left curve
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.trackSection(0, 1,true,true)));

		//Right curve
		layout.add(c3d.translate3D(0, 0, 5)
				.add(sTrackBuilder.trackSection(0, 1, true, false)));

		return layout;
	}

	public Module3D handleBar()
	{
		double handlePosX = -60;
		double handlePosY = 10;
		double handleHeight = 10;
		double handleDiameter = 6;
		Module3D handle = csg.csg3D().shapes3D().cylinder3D(handleDiameter, handleHeight, 64);
		handle = c3d.translate3D(handlePosX,handlePosY,0.5*handleHeight).add(handle);
		Module3D cyl1 = csg.csg3D().shapes3D().cylinder3D(10, 5, 64);
		cyl1 = c3d.translate3D(0,0,2.5).add(cyl1);
		Module3D cyl2 = c3d.translate3D(50, 0, 0).add(cyl1);
		Module3D cyl3 = c3d.translate3D(handlePosX, handlePosY, 0).add(cyl1);
		Module3D hull1 = c3d.hull3D().add(cyl1).add(cyl2);
		Module3D hull2 = c3d.hull3D().add(cyl1).add(cyl3);
		Module3D union = c3d.union3D().add(hull1).add(hull2).add(handle);
		Module3DFrom3D diff = c3d.difference3D().add(union);
		Module3D clickHole = clickSystem.roundClickerCutout(5);
		diff.add(clickHole);
		diff.add(c3d.translate3D(50, 0, 0).add(clickHole));
		return c3d.translate3D(-25, 40, 0).add(diff);
	}

	public Module3D crossSection()
	{
		Module3DFrom3D tracks = c3d.union3D();
		Module3DFrom3D trackCutout = c3d.difference3D().add(tracks);

		//Tracks
		double fBegin = getF(crossBegin);
		double fEnd = getF(crossEnd);
		tracks.add(track(fBegin, fEnd, false,true));
		tracks.add(track(fBegin, fEnd,true, false));

		Module3D curveCut = trackCutout(fBegin, fEnd,true, false, true);
		curveCut = c3d.translate3D(0,0,10).add(curveCut);
		trackCutout.add(curveCut);
		curveCut = trackCutout(fBegin, fEnd,true, false, false);
		curveCut = c3d.translate3D(0,0,15).add(curveCut);
		trackCutout.add(curveCut);

		Module3D straightCut = trackCutout(fBegin, fEnd,false, true, true);
		straightCut = c3d.translate3D(0,0,10).add(straightCut);
		trackCutout.add(straightCut);

		Module3DFrom3D cross = c3d.union3D().add(trackCutout);

		//Mounts
		cross.add(transform(fBegin,true, trackMount(true, false, false, 0)));
		cross.add(transform(fEnd, true, trackMount(false, true, false, 0)));
		cross.add(transform(fBegin, false, trackMount(true, true, true, 0)));
		cross.add(transform(fEnd, false, trackMount(false, false, true, 0)));

		return cross;
	}

	public Module3D switchTrack(boolean curve)
	{
		boolean left = !curve;
		double fBegin = mmToFOuterCurve(15);
		double fEnd = getF(turnSleeper);
		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D cutout = c3d.difference3D().add(solid);
		Module3D track = track(
				fBegin,
				fEnd + mmToFOuterCurve(adjust.solidRoundLoose().xy()),
				curve,
				left,
				fBegin,
				fEnd - mmToFOuterCurve(5),
				true,
				false);
		solid.add(track);
		cutout.add(trackCutout(fBegin, fEnd, !curve, left, false));

		//Turn hole
		Module3D turnMount = roundTrackMountBase(false, !left, left, adjust.solidRoundLoose().xy());
		solid.add(transform(fEnd, curve, turnMount));
		Module3D turnClickHole = roundClickHole(false, !left, left);
		turnClickHole = c3d.translate3D(0,0,5).add(turnClickHole);
		cutout.add(transform(fEnd, curve, turnClickHole));

		//Actuator hole
		double fActuator = 0.5*(getF(0) + getF(1));
		Module3D actuatorMount = roundTrackMountBase(false, !left, left, 0);
		solid.add(transform(fActuator, curve, actuatorMount));
		Module3D actuatorClickHole = roundClickHole(false, !left, left);
		actuatorClickHole = c3d.translate3D(0,0,5).add(actuatorClickHole);
		cutout.add(transform(fActuator, curve, actuatorClickHole));

		return cutout;
	}

	public Module3D allSleepers()
	{
		Module3DFrom3D union = c3d.union3D();
		for(int i = 0; i < numberOfSleepers; ++i)
		{
			union.add(sleeper(i));
		}
		return union;
	}

	public Module3D sleeper(int n)
	{
		double f = getF(n);
		Module3D base = csg.csg3D().shapes3D().box3D(sleeperLength, sleeperWidth, 5);
		base = c3d.translate3D(0,0,2.5).add(base);
		Module3D left = transform(f, true, base);
		Module3D right = transform(f, false, base);
		Module3DFrom3D solid = c3d.union3D().add(left).add(right);
		Module3DFrom3D diff = c3d.difference3D().add(solid);
		int columns = 6;
		if(n == turnSleeper)
		{
			columns = 4;
			diff.add(transform(f, false, roundClickHole(false, false, true)));
			diff.add(transform(f, true, roundClickHole(false, true, false)));
			diff.add(transform(f, false, clickHole(true, true, true)));
			diff.add(transform(f, true, clickHole(true, false, false)));
			diff.add(transform(f, true, clickerHolesLeft(3)));
			diff.add(transform(f, false, clickerHolesRight(3)));
			if(n != crossBegin)
			{
				diff.add(transform(f, false, clickHole(true, false, true)));
				diff.add(transform(f, true, clickHole(true, true, false)));
			}
			//solid.add(curveTransform(f, roundMount(false, true, false, adjust.solidRoundLoose().xy())));
		}
		else if(n == crossBegin)
		{
			columns = 7;
			diff.add(transform(f, true, clickHole(true, false, false)));
			diff.add(transform(f, false, clickHole(true, true, true)));
			diff.add(transform(f, false, clickHole(false, true, true)));
			diff.add(transform(f, false, clickHole(false, false, true)));
			diff.add(transform(f, true, clickHole(false, true, false)));
			diff.add(transform(f, true, clickHole(false, false, false)));
		}
		else if(n == crossEnd)
		{
			columns = 10;
			diff.add(transform(f, false,clickHole(true, true, true)));
			diff.add(transform(f, true, clickHole(true, false, false)));
		}
		if(n > crossEnd)
		{
			columns = 11;
		}
		diff.add(transform(f, true, clickerHolesLeft(columns)));
		diff.add(transform(f, false, clickerHolesRight(columns)));
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
				double posX = 0.5* xTranslate - 5 - x*10;
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

	private Module3D transform(double f, boolean curve, Module3D child)
	{
		return sTrackBuilder.transform(f, curve, child);
	}

	public Module3D stdTrack(int beginSleeper, int endSleeper, boolean curve, boolean left, boolean insideMounts)
	{
		Module3DFrom3D res = c3d.union3D();
		int length = endSleeper - beginSleeper;
		double[] fArray = new double[length+1];
		for(int i = 0; i <= length; ++i)
		{
			fArray[i] = getF(beginSleeper + i);
		}
		Module3D track = track(fArray[0], fArray[length], curve, left);
		res.add(track);

		//Mounts
		//Begin
		res.add(transform(fArray[0], curve, trackMount(true, true, left, 0)));
		res.add(transform(fArray[0], curve, trackMount(true, false, left, 0)));

		//End
		res.add(transform(fArray[length], curve, trackMount(false, true, left, 0)));
		res.add(transform(fArray[length], curve, trackMount(false, false, left, 0)));
		for(int i = 1; i < length; ++i)
		{
			//Middle outside
			res.add(transform(fArray[i], curve, trackMount(true, left, left, 0)));
			res.add(transform(fArray[i], curve, trackMount(false, left, left, 0)));

			if (insideMounts)
			{
				//Middle inside
				res.add(transform(fArray[i], curve, trackMount(true, !left, left, 0)));
				res.add(transform(fArray[i], curve, trackMount(false, !left, left, 0)));
				return res;
			}
		}
		return res;
	}

	private Module3D track(double fBegin, double fEnd, boolean curve, boolean left)
	{
		Module3D track = sTrackBuilder.trackSection(fBegin, fEnd, curve, left);
		return c3d.translate3D(0,0,5).add(track);
	}

	private Module3D track(double fBegin, double fEnd, boolean curve, boolean left,
						   double footBegin, double footEnd,
	                       boolean insideFoot, boolean outsideFoot)
	{
		Module3D track = sTrackBuilder.trackSection(fBegin, fEnd, curve, left,
													footBegin, footEnd, insideFoot, outsideFoot);
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
				xTranslate, false, angularResolution);
	}

	private Module3D trackCutout(double fBegin, double fEnd, boolean curve, boolean left, boolean insideTrack)
	{
		if(curve)
		{
			ParallelSwitchTrackBuilder cutBuilder = cutTrackBuilder(insideTrack);
			return cutBuilder.trackSection(fBegin, fEnd, true, left);
		}
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

	private double mmToFOuterCurve(double mm)
	{
		return sTrackBuilder.mmToFOuterCurve(mm);
	}
}
