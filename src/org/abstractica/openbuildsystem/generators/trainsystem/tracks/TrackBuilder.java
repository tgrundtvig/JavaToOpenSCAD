package org.abstractica.openbuildsystem.generators.trainsystem.tracks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.math.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.openbuildsystem.Print3DAdjust;

import java.util.ArrayList;
import java.util.List;

public class TrackBuilder
{
	private final CSG csg;
	private final Construct3D c3d;
	private final Print3DAdjust adjust;
	private final double trackWidth;
	private final double trackHeight;
	private final double toothWidth;
	private final double toothHeight;

	public TrackBuilder(CSG csg, Print3DAdjust adjust, double trackWidth, double trackHeight, double toothWidth, double toothHeight)
	{
		this.csg = csg;
		this.c3d = csg.csg3D().construct3D();
		this.adjust = adjust;
		this.trackWidth = trackWidth;
		this.trackHeight = trackHeight;
		this.toothWidth = toothWidth;
		this.toothHeight = toothHeight;
	}

	public Module3D straightTrack(double length, boolean teeth)
	{
		Module2D profile = csg.csg2D().shapes2D().rect2D(trackWidth, trackHeight);
		Module3D track = c3d
				.linearExtrude(length, 2).add(profile);
		track = c3d.rotateX(Angle.degrees(90)).add(track);
		track = c3d.translate3D(0,0.5*length,0.5*trackHeight).add(track);
		if(!teeth) return track;
		//Create teeth
		int n = (int) Math.floor(length / toothWidth);
		double d = length / n;
		Module3D t = c3d.translate3D(0, 0, trackHeight-0.01).add(tooth());
		Module3DFrom3D union = c3d.union3D();
		union.add(track);
		for(int i = 0; i < n; ++i)
		{
			union.add(c3d.translate3D(0, 0.5*d + i*d, 0).add(t));
		}
		return union;
	}

	public Module3D straightTrackSection(double fBegin, double fEnd, double totalLength, boolean teeth)
	{
		if(fBegin >= fEnd) throw new RuntimeException("fBegin >= fEnd");
		TrackBuilder tb = new TrackBuilder(
				csg, adjust, trackWidth + 2,
				trackHeight + toothHeight + 2,
				0, 0);
		double length = (fEnd - fBegin)*totalLength;
		Module3D intersectTrack = tb.straightTrack(length, false);
		intersectTrack = c3d.translate3D(0,fBegin*totalLength, -1).add(intersectTrack);
		Module3D track = straightTrack(totalLength, teeth);
		return c3d.intersection3D().add(track).add(intersectTrack);
	}

	public Module3D curvedTrack(double radius,
	                            Angle angle,
	                            boolean teeth,
	                            int angularResolution)
	{

		Module2D profile = csg.csg2D().shapes2D().rect2D(trackWidth, trackHeight);
		Module2D tp = csg.csg2D().construct2D().translate2D(radius, 0.5*trackHeight).add(profile);
		Module3D track = c3d.rotateExtrude(angle, angularResolution, 2).add(tp);
		if(!teeth) return c3d.translate3D(-radius, 0,0).add(track);
		//Create teeth
		double innerLength = (radius-0.5*trackWidth) * angle.asRadians();
		int n = (int) Math.floor(innerLength / toothWidth);
		double d = angle.asRotations() / n;
		Module3D t = c3d.translate3D(radius, 0, trackHeight-0.01).add(tooth());
		Module3DFrom3D union = c3d.union3D();
		union.add(track);
		for(int i = 0; i < n; ++i)
		{
			Angle a = Angle.rotations(0.5*d + i*d);
			union.add(c3d.rotateZ(a).add(t));
		}
		return c3d.translate3D(-radius, 0,0).add(union);
	}

	public Module3D curvedTrackSection(double fBegin, double fEnd,
	                                   double radius, Angle angle,
	                                   boolean teeth, int angularResolution)
	{
		if(fBegin >= fEnd) throw new RuntimeException("fBegin >= fEnd");
		double intersectWidth = trackWidth + 2;
		double intersectHeight = trackHeight + toothHeight + 2;
		TrackBuilder tb = new TrackBuilder(csg, adjust, intersectWidth, intersectHeight, 0, 0);
		double a = (fEnd - fBegin)*angle.asRotations();
		Module3D intersectTrack = tb.curvedTrack(radius,Angle.rotations(a), false, angularResolution);
		intersectTrack = c3d.translate3D(0,0,-1).add(intersectTrack);
		intersectTrack = c3d.rotateAroundZ(Angle.rotations(fBegin*angle.asRotations()), -radius, 0).add(intersectTrack);
		Module3D track = curvedTrack(radius, angle, teeth, angularResolution);
		Module3D res = c3d.intersection3D().add(track).add(intersectTrack);
		return res;
	}

	private Module3D tooth()
	{
		//noinspection SuspiciousNameCombination This is actually ok and what it should do.
		Module3D t = c3d.linearExtrude(trackWidth, 2).add(toothProfile());
		return c3d.rotate3D(Angle.degrees(90), Angle.ZERO, Angle.degrees(90)).add(t);
	}

	private Module2D toothProfile()
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0.5*toothWidth, 0));
		points.add(Vector2D.create(0, toothHeight));
		points.add(Vector2D.create(-0.5*toothWidth, 0));
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
