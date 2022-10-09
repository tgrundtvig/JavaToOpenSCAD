package org.abstractica.openbuildsystem.unused;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.CSG3D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.openbuildsystem.Adjust;

import java.util.ArrayList;
import java.util.List;

public class PrintedTracks
{
	private static final double TURN_DIAMETER = 1200;
	private static final double TRACK_AXEL_WIDTH = 80;
	private static final double SLEEPER_WIDTH = 120;
	private static final Angle TRACK_ANGLE = Angle.degrees(5);
	private static final double TRACK_WIDTH = 3;
	private static final double TRACK_MID_HEIGHT = 10;
	private static final double SCREW_HEAD_DIAMETER = 6;
	private static final double SCREW_HEAD_HEIGHT = 3;
	private static final double SCREW_DIAMETER = 3;
	private static final double NUT_WIDTH = 5.5;

	public Module3D sleeperMid(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();

		Module3DFrom3D diff = c3D.difference3D();
		diff.add(sleeperBase(csg, adjust));
		diff.add(nutHoles(csg, adjust));
		return diff;
	}

	public Module3D sleeperShared(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Module3DFrom3D diff = c3D.difference3D();
		diff.add(sleeperBase(csg, adjust));
		diff.add(c3D.translate3D(0,-5,0).add(nutHoles(csg, adjust)));
		diff.add(c3D.translate3D(0,+5,0).add(nutHoles(csg, adjust)));
		return diff;
	}

	public Module3D curvedOuterTrack(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();

		Module3D track = curvedTrack(0.5*TURN_DIAMETER + 0.5*TRACK_AXEL_WIDTH, trackProfile(csg, adjust), csg, adjust);
		return c3D.translate3D(-0.5*TURN_DIAMETER,0,0).add(track);
	}

	public Module3D curvedInnerTrack(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();

		Module2D profile = csg.csg2D().construct2D().mirror2D(1,0).add(trackProfile(csg, adjust));
		Module3D track = curvedTrack(0.5*TURN_DIAMETER - 0.5*TRACK_AXEL_WIDTH, profile, csg, adjust);
		return c3D.translate3D(-0.5*TURN_DIAMETER,0,0).add(track);
	}

	private Module3D curvedTrack(double turnDiameter, Module2D profile, CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();

		Module3DFrom2D track = c3D.rotateExtrude(Angle.rotations(1.0/16), 1024, 4);
		track.add(csg.csg2D().construct2D().translate2D(turnDiameter,0).add(profile));
		Module3DFrom3D union = c3D.union3D();
		union.add(track);
		Module3D trackMountEnd = trackMountEnd(csg, adjust);
		Module3D end1 = c3D.translate3D(turnDiameter,5, 0).add(trackMountEnd);
		union.add(end1);
		Module3D end2 = c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.degrees(180)).add(trackMountEnd);
		end2 = c3D.translate3D(turnDiameter, -5, 0).add(end2);
		end2 = c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0/16)).add(end2);
		union.add(end2);
		Module3D mid = trackMountMid(csg, adjust);
		mid = c3D.translate3D(turnDiameter,0,0).add(mid);
		mid = c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0/32)).add(mid);
		union.add(mid);

		//union.add(c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.degrees(180)).add(trackMountEnd));
		//union.add(trackMountMid(csg, adjust));

		return union;
	}

	private Module3D sleeperBase(CSG csg, Adjust adjust)
	{
		return csg.csg3D().construct3D().translate3D(0,0,2.5).add(
				csg.csg3D().shapes3D().box3D(SLEEPER_WIDTH, 20, 5));
	}

	private Module3D nutHoles(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();

		Module3D screw = s3D.cylinder3D(3+2*adjust.getXYHoleAdjust(), 7, 32);
		Module3D nut = s3D.cylinder3D(
				hexDiameter(NUT_WIDTH + 2* adjust.getXYHoleAdjust()),
				4, 6);
		nut = c3D.translate3D(0,0,2 + 2.5 - 3).add(nut);
		Module3DFrom3D union = c3D.union3D();
		Module3D nutHole =  c3D.translate3D(0,0,2.5).add(union.add(screw).add(nut));
		Module3DFrom3D holes = c3D.union3D();
		holes.add(c3D.translate3D(0.5*TRACK_AXEL_WIDTH - 5, 0, 0).add(nutHole));
		holes.add(c3D.translate3D(0.5*TRACK_AXEL_WIDTH + 5, 0, 0).add(nutHole));
		holes.add(c3D.translate3D(-0.5*TRACK_AXEL_WIDTH - 5, 0, 0).add(nutHole));
		holes.add(c3D.translate3D(-0.5*TRACK_AXEL_WIDTH + 5, 0, 0).add(nutHole));
		//Control brick mount
		holes.add(c3D.translate3D(-25, 0, 0).add(nutHole));
		holes.add(c3D.translate3D(25, 0, 0).add(nutHole));
		return holes;
	}

	public Module3D straightTrack(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();
		Module2D profile = trackProfile(csg, adjust);
		Module3DFrom3D union = c3D.union3D();
		Module3D track = c3D.linearExtrude(2*SLEEPER_WIDTH-2* adjust.getXYHoleAdjust(), 4).add(profile);
		union.add(c3D.rotate3D(Angle.degrees(90), Angle.ZERO, Angle.ZERO).add(track));
		Module3D trackMountEnd = c3D.translate3D(0,-SLEEPER_WIDTH+5+ adjust.getXYHoleAdjust(), 0).add(trackMountEnd(csg, adjust));
		union.add(trackMountEnd);
		union.add(c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.degrees(180)).add(trackMountEnd));
		union.add(trackMountMid(csg, adjust));
		return union;
	}

	public Module2D trackProfile(CSG csg, Adjust adjust)
	{
		double heightDiff = Angle.sin(TRACK_ANGLE)*TRACK_WIDTH;
		List<Vector2D> profilePoints = new ArrayList<>();
		profilePoints.add(Vector2D.create(-0.5*TRACK_WIDTH, 0));
		profilePoints.add(Vector2D.create(0.5*TRACK_WIDTH, 0));
		profilePoints.add(Vector2D.create(0.5*TRACK_WIDTH, TRACK_MID_HEIGHT+0.5*heightDiff));
		profilePoints.add(Vector2D.create(-0.5*TRACK_WIDTH, TRACK_MID_HEIGHT-0.5*heightDiff));
		return csg.csg2D().construct2D().polygon2D(profilePoints);
	}

	public Module3D trackMountMid(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();

		Module3D cyl = s3D.cylinder3D(10, 5, 64);
		Module3D base = c3D.hull3D().add(c3D.translate3D(-5,0,0).add(cyl))
				.add(c3D.translate3D(5,0,0).add(cyl));
		return trackMount(base, csg, adjust);
	}

	public Module3D trackMountEnd(CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();

		Module3D cyl = s3D.cylinder3D(10, 5, 64);
		Module3D base = c3D.hull3D().add(c3D.translate3D(-5,0,0).add(cyl))
				.add(c3D.translate3D(5,0,0).add(cyl));
		Module3DFrom3D baseUnion = c3D.union3D().add(base);
		baseUnion.add(c3D.translate3D(0,-2.5, 0).add(s3D.box3D(20, 5, 5)));
		return trackMount(baseUnion, csg, adjust);
	}

	public Module3D trackMount(Module3D base, CSG csg, Adjust adjust)
	{
		CSG3D csg3D = csg.csg3D();
		Construct3D c3D = csg3D.construct3D();
		Shapes3D s3D = csg3D.shapes3D();

		Module3D screw = s3D.cylinder3D(SCREW_DIAMETER + 2* adjust.getXYHoleAdjust(),
										7, 32);
		Module3DFrom3D diff = c3D.difference3D();
		diff.add(base);
		diff.add(c3D.translate3D(-5, 0, 0).add(screw));
		diff.add(c3D.translate3D(5, 0, 0).add(screw));

		Module3D screwHead = s3D.cylinder3D(SCREW_HEAD_DIAMETER + 2* adjust.getXYHoleAdjust(),
											SCREW_HEAD_HEIGHT + 1, 32);
		screwHead = c3D.translate3D(0,0, 0.5*(SCREW_HEAD_HEIGHT + 1) + 2.5 - SCREW_HEAD_HEIGHT).add(screwHead);
		diff.add(c3D.translate3D(-5, 0, 0).add(screwHead));
		diff.add(c3D.translate3D(5, 0, 0).add(screwHead));
		return c3D.translate3D(0,0,2.5).add(diff);
	}

	private double hexDiameter(double width)
	{
		return (2*width) / Math.sqrt(3.0);
	}
/*
	private double hexWidth(double diameter)
	{

	}*/
}
