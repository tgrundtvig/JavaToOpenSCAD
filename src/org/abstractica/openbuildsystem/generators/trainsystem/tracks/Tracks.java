package org.abstractica.openbuildsystem.generators.trainsystem.tracks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;

public class Tracks
{
	private final CSG csg;
	private final Construct3D c3d;
	private final Print3DAdjust adjust;
	private final TrackBuilder trackBuilder;
	private final TrackMount trackMount;
	private final ClickSystem clickSystem;
	private final double trackGauge = 80;

	public enum MountType {LEFT, RIGHT, BOTH}
	public Tracks(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.c3d = csg.csg3D().construct3D();
		this.adjust = adjust;
		this.clickSystem = new ClickSystemImpl(csg, adjust,5);
		this.trackBuilder = new TrackBuilder(csg, adjust,5, 10, 2, 1);
		this.trackMount = new TrackMount(csg, adjust, clickSystem);
	}

	public Module3D straightTrack(double length)
	{
		return straightTrack(length, 0, MountType.BOTH);
	}

	public Module3D straightTrack(double length, int middleMounts)
	{
		return straightTrack(length, middleMounts, MountType.BOTH);
	}

	public Module3D leftStraightTrack(double length, int middleMounts, MountType middleMountType)
	{
		return c3d.translate3D(-0.5*trackGauge, 0,0)
				.add(straightTrack(length, middleMounts, middleMountType));
	}

	public Module3D rightStraightTrack(double length, int middleMounts, MountType middleMountType)
	{
		return c3d.translate3D(0.5*trackGauge, 0,0)
				.add(straightTrack(length, middleMounts, middleMountType));
	}

	public Module3D outerCurvedTrack(double turnRadius, Angle angle)
	{
		return outerCurvedTrack(turnRadius, angle, 0, MountType.BOTH);
	}

	public Module3D outerCurvedTrack(double turnRadius, Angle angle, int middleMounts)
	{
		return outerCurvedTrack(turnRadius, angle, middleMounts, MountType.BOTH);
	}

	public Module3D outerCurvedTrack(double turnRadius, Angle angle, int middleMounts, MountType middleMountType)
	{
		double trackRadius = turnRadius + 0.5* trackGauge;
		Module3DFrom3D union = c3d.union3D();
		Module3D track = trackBuilder.curvedTrack(trackRadius, angle, true, 1024);
		union.add(track);

		//Add mounts
		union.add(curvedMounts(trackRadius, angle, middleMounts, middleMountType));
		union = c3d.translate3D(0.5*trackGauge, 0, 0).add(union);
		return union;
	}

	public Module3D innerCurvedTrack(double turnRadius, Angle angle)
	{
		return innerCurvedTrack(turnRadius, angle, 0, MountType.BOTH);
	}

	public Module3D innerCurvedTrack(double turnRadius, Angle angle, int middleMounts)
	{
		return innerCurvedTrack(turnRadius, angle, middleMounts, MountType.BOTH);
	}

	public Module3D innerCurvedTrack(double turnRadius, Angle angle, int middleMounts, MountType middleMountType)
	{
		double trackRadius = turnRadius - 0.5*trackGauge;
		Module3DFrom3D union = c3d.union3D();
		Module3D track = trackBuilder.curvedTrack(trackRadius, angle, false, 1024);
		union.add(track);

		//Add mounts
		union.add(curvedMounts(trackRadius, angle, middleMounts, middleMountType));
		union = c3d.translate3D(-0.5*trackGauge, 0, 0).add(union);
		return union;
	}

	public Module3D sleeper()
	{
		Module3DFrom3D solid = c3d.union3D();

		//Base sleeper
		Module3D base = csg.csg3D().shapes3D().box3D(120, 20, 5);
		base = c3d.translate3D(0,0,2.5).add(base);
		solid.add(base);

		//Code block mounts
		Module3D block = csg.csg3D().shapes3D().box3D(10, 20, 5);
		Module3D blockLeft = c3d.translate3D(-0.5* trackGauge +15, 0, 7.5).add(block);
		solid.add(blockLeft);
		Module3D blockRight = c3d.translate3D(0.5* trackGauge -15, 0, 7.5).add(block);
		solid.add(blockRight);

		Module3DFrom3D diff = c3d.difference3D().add(solid);
		//Add clickerCutouts
		for(int y = -5; y <= 5; y=y+10)
		{
			for (int x = -55; x <= 55; x = x + 10)
			{
				diff.add(c3d.translate3D(x, y, 0).add(clickSystem.clickerCutout(5)));
			}
		}

		for(int y = -5; y <= 5; y=y+10)
		{
			diff.add(c3d.translate3D(-0.5* trackGauge +15, y, 5).add(clickSystem.clickerCutout(5)));
			diff.add(c3d.translate3D(0.5* trackGauge -15, y, 5).add(clickSystem.clickerCutout(5)));
		}

		return diff;
	}

	private Module3D straightTrack(double length, int middleMounts, MountType middleMountType)
	{
		Module3DFrom3D union = c3d.union3D();
		Module3D track = trackBuilder.straightTrack(length, true);
		union.add(track);

		//Add mounts
		union.add(straightMounts(length, middleMounts, middleMountType));

		return union;
	}

	private Module3D straightMounts(double length, int middleMounts, MountType middleMountType)
	{
		Module3DFrom3D res = c3d.union3D();
		//Begin mount
		Module3D beginMount = trackMount.beginMount();
		res.add(beginMount);
		//Middle mounts
		if(middleMounts > 0)
		{
			Module3D middleMount = switch(middleMountType)
					{
						case LEFT -> trackMount.leftDoubleMount();
						case RIGHT -> trackMount.rightDoubleMount();
						case BOTH -> trackMount.doubleMount();
					};
			double dist = length / (middleMounts+1);
			for(int i = 0; i < middleMounts; ++i)
			{
				res.add(c3d.translate3D(0, dist + (i*dist), 0).add(middleMount));
			}
		}
		//End mount
		Module3D endMount = trackMount.endMount();
		endMount = c3d.translate3D(0, length, 0).add(endMount);
		res.add(endMount);
		return res;
	}

	private Module3D curvedMounts(double trackRadius, Angle angle, int middleMounts, MountType middleMountType)
	{
		Module3DFrom3D res = c3d.union3D();

		//begin mount
		Module3D beginMount = trackMount.beginMount();
		beginMount = c3d.translate3D(trackRadius, 0, 0).add(beginMount);
		res.add(beginMount);

		//Middle mounts
		if(middleMounts > 0)
		{
			double da = angle.asRotations() / (middleMounts + 1);
			Module3D middleMount = switch(middleMountType)
					{
						case LEFT -> trackMount.leftDoubleMount();
						case RIGHT -> trackMount.rightDoubleMount();
						case BOTH -> trackMount.doubleMount();
					};
			middleMount = c3d.translate3D(trackRadius, 0, 0).add(middleMount);
			for(int i = 0; i < middleMounts; ++i)
			{
				res.add(c3d.rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(da + i*da)).add(middleMount));
			}
		}

		//End mount
		Module3D mountEnd = c3d.translate3D(trackRadius, 0, 0).add(trackMount.endMount());
		mountEnd = c3d.rotate3D(Angle.ZERO, Angle.ZERO, angle).add(mountEnd);
		res.add(mountEnd);
		res = c3d.translate3D(-trackRadius, 0, 0).add(res);
		return res;
	}
}
