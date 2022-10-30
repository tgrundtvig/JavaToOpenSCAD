package org.abstractica.openbuildsystem.generators.trainsystem.tracks.switches;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.math.Vector3D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.trainsystem.tracks.TrackBuilder;

public class ParallelSwitchTrackBuilder
{
	private final double footWidth = 20;
	private final CSG csg;
	private final Construct3D c3d;
	private final Print3DAdjust adjust;
	private final TrackBuilder trackBuilder;
	private final double trackGauge;
	private final double totalLength;
	private final double sleeperLength;

	private final double radius;
	private final Angle angle;
	private final boolean teeth;
	private final int angularResolution;


	public ParallelSwitchTrackBuilder(CSG csg, Print3DAdjust adjust, TrackBuilder trackBuilder, double trackGauge,
	                                  double totalLength, double sleeperLength, boolean teeth, int angularResolution)
	{
		this.csg = csg;
		this.c3d = csg.csg3D().construct3D();
		this.adjust = adjust;
		this.trackBuilder = trackBuilder;
		this.trackGauge = trackGauge;
		this.totalLength = totalLength;
		this.sleeperLength = sleeperLength;
		double halfTotalLength = 0.5*totalLength;
		double halfXTranslate = 0.5*sleeperLength;
		this.radius = (halfXTranslate*halfXTranslate + halfTotalLength*halfTotalLength)/sleeperLength;
		System.out.println("Radius: " + radius);
		double a = Math.asin(halfTotalLength/radius);
		this.angle = Angle.radians(a);
		System.out.println("Angle: " + angle.asDegrees());
		this.teeth = teeth;
		this.angularResolution = angularResolution;
	}

	public Module3D trackSection(double fBegin, double fEnd, boolean curve, boolean left)
	{
		if(fBegin >= fEnd) throw new RuntimeException("fBegin >= fEnd");
		if(!curve)
		{
			return trackSection(fBegin, fEnd, false, left, 0, 0, false, false);
		}
		Module3DFrom3D union = c3d.union3D();
		if(fBegin < 0.5)
		{
			double end = Math.min(1.0, 2*fEnd);
			union.add(curveTrackBegin(2*fBegin, end, left));
		}
		if(fEnd > 0.5)
		{
			double start = Math.max(0.0, 2*(fBegin-0.5));
			union.add(curveTrackEnd( start, 2*(fEnd-0.5), left));
		}
		return union;
	}

	public Module3D trackSection(   double fBegin, double fEnd, boolean curve, boolean left,
									double footBegin, double footEnd,
	                                boolean insideFoot, boolean outsideFoot)
	{
		if(fBegin >= fEnd) throw new RuntimeException("fBegin >= fEnd");
		Module3D track = curve ?
				trackSection(fBegin, fEnd, true, left) :
				trackBuilder.straightTrackSection(fBegin, fEnd, totalLength, true);
		double tx = 0;
		if(!curve)
		{
			tx = left ? -0.5*trackGauge : 0.5*trackGauge;
			track = c3d.translate3D(tx, 0, 0).add(track);
		}
		if(!insideFoot && !outsideFoot)
		{
			return track;
		}
		Module3DFrom3D union = c3d.union3D();

		union.add(track);
		double trackWidth = (insideFoot && outsideFoot) ? footWidth : 0.5*footWidth;
		TrackBuilder tb = new TrackBuilder(csg, adjust, trackWidth, 5, 2, 1);
		if(curve)
		{
			double gauge = trackGauge;
			if (!(insideFoot && outsideFoot))
			{
				gauge += insideFoot ? -0.5 * footWidth : 0.5 * footWidth;
			}
			ParallelSwitchTrackBuilder feetBuilder = new ParallelSwitchTrackBuilder(
					csg, adjust, tb, gauge, totalLength, sleeperLength, false, angularResolution);
			Module3D feet = feetBuilder.trackSection(footBegin, footEnd,true, left);
			union.add(feet);
		}
		else
		{
			double footLength = footEnd - footBegin;
			Module3D feet = tb.straightTrack(footLength*totalLength, false);
			double t = 0;
			if(!(insideFoot && outsideFoot))
			{
				t = insideFoot ? 5 : -5;
			}
			feet = c3d.translate3D(tx+t,footBegin*totalLength,0).add(feet);
			union.add(feet);
		}
		return union;
	}

	public Module3D transform(double f, boolean curve, Module3D child)
	{
		if(!curve)
		{
			return c3d.translate3D(0, f*totalLength, 0).add(child);
		}
		if(f <= 0.5)
		{
			return c3d.rotateAroundZ(Angle.rotations(f*angle.asRotations()*2.0), -radius, 0).add(child);
		}
		double totalLength = Angle.sin(angle)*radius*2.0;
		double xt = (1.0 - Angle.cos(angle))*radius*2.0;
		Module3D res = c3d.translate3D(-xt, totalLength, 0).add(child);
		double a = 1.0 - f;
		res = c3d.rotateAroundZ(Angle.rotations(a * angle.asRotations()*2.0),
				-xt + radius, totalLength).add(res);
		return res;
	}

	public double mmToFStraight(double mm)
	{
		return mm / totalLength;
	}

	public double mmToFInnerCurve(double mm)
	{
		double r = radius - 0.5*trackGauge;
		return mmToFCurve(mm, r);
	}

	public double mmToFOuterCurve(double mm)
	{
		double r = radius + 0.5*trackGauge;
		return mmToFCurve(mm, r);
	}

	private double mmToFCurve(double mm, double r)
	{
		return (0.5 * mm) / (r*angle.asRadians());
	}

	private Module3D curveTrackBegin(double fBegin, double fEnd, boolean left)
	{
		double tx = left ? -0.5*trackGauge : 0.5*trackGauge;
		Module3D track = trackBuilder.curvedTrackSection(
				fBegin, fEnd, radius + tx, angle, !left && teeth, angularResolution);
		track = c3d.translate3D(tx, 0,0).add(track);
		return track;
	}

	private Module3D curveTrackEnd(double fBegin, double fEnd, boolean left)
	{
		double tx = left ? 0.5*trackGauge : -0.5*trackGauge;
		//Left
		Module3D res = trackBuilder.curvedTrackSection(
				fBegin, fEnd, radius + tx, angle, left && teeth, angularResolution);
		res = c3d.translate3D(tx, 0, 0).add(res);
		res = c3d.mirror3D(Vector3D.create(1,0,0)).add(res);
		res = c3d.rotateAroundZ(angle, -radius, 0).add(res);
		return res;
	}
}
