package org.abstractica.openbuildsystem.unused;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;

public class LaserTracks
{
	private static final double TURN_DIAMETER = 1200;
	private static final double TRACK_WIDTH = 80;
	private static final double SLEEPER_WIDTH = 120;

	public Module2D getMidSleeper(CSG csg)
	{
		Module2D rect = csg.csg2D().shapes2D().rect2D(SLEEPER_WIDTH, 20);
		Module2D hole = csg.csg2D().shapes2D().circle2D(2, 32);
		double t = 0.5*TRACK_WIDTH + 10;
		Module2DFrom2D t1 = csg.csg2D().construct2D().translate2D(-t, 5);
		Module2DFrom2D t2 = csg.csg2D().construct2D().translate2D(-t, -5);
		Module2DFrom2D t3 = csg.csg2D().construct2D().translate2D(t, 5);
		Module2DFrom2D t4 = csg.csg2D().construct2D().translate2D(t, -5);
		Module2DFrom2D res = csg.csg2D().construct2D().difference2D();
		res.add(rect);
		res.add(t1.add(hole));
		res.add(t2.add(hole));
		res.add(t3.add(hole));
		res.add(t4.add(hole));
		return res;
	}

	public Module2D getStraightTrack(CSG csg)
	{
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		Module2D track = csg.csg2D().shapes2D().rect2D(10, 2*SLEEPER_WIDTH);
		Module2DFrom2D trackT = csg.csg2D().construct2D().translate2D(0.5*TRACK_WIDTH + 5, 0);
		track = trackT.add(track);
		union.add(track);
		Module2DFrom2D endHoldT = csg.csg2D().construct2D().translate2D(0.5*TRACK_WIDTH + 5, -SLEEPER_WIDTH);
		union.add(endHoldT.add(endHold(csg)));
		Module2DFrom2D endHoldM = csg.csg2D().construct2D().mirror2D(0,1);
		union.add(endHoldM.add(endHoldT.add(endHold(csg))));
		Module2DFrom2D midHoleT = csg.csg2D().construct2D().translate2D(0.5*TRACK_WIDTH + 5, 0);
		union.add(midHoleT.add(midHold(csg)));
		Module2DFrom2D diff = csg.csg2D().construct2D().difference2D().add(union);
		Module2D hole = csg.csg2D().shapes2D().circle2D(3, 32);
		double tx = 0.5*TRACK_WIDTH + 10;
		Module2D h1 = csg.csg2D().construct2D().translate2D(tx, 5).add(hole);
		Module2D h2 = csg.csg2D().construct2D().translate2D(tx, -5).add(hole);
		Module2D h3 = csg.csg2D().construct2D().translate2D(tx, SLEEPER_WIDTH-5).add(hole);
		Module2D h4 = csg.csg2D().construct2D().translate2D(tx, -SLEEPER_WIDTH+5).add(hole);
		diff.add(h1);
		diff.add(h2);
		diff.add(h3);
		diff.add(h4);

		return diff;
		//return endHold(csg);
	}

	public Module2D getOuterCurvedTrack(CSG csg)
	{
		Module2D outerCircle = csg.csg2D().shapes2D().circle2D(TURN_DIAMETER + TRACK_WIDTH + 20, 1024);
		Module2D innerCircle = csg.csg2D().shapes2D().circle2D(TURN_DIAMETER + TRACK_WIDTH, 1024);
		Module2D cutPie = csg.csg2D().shapes2D().pie2D(TURN_DIAMETER + TRACK_WIDTH + 120, Angle.ZERO, Angle.rotations(1.0/16), 16);
		Module2D allTrack = csg.csg2D().construct2D().difference2D().add(outerCircle).add(innerCircle);
		Module2D trackSegment = csg.csg2D().construct2D().intersection2D().add(allTrack).add(cutPie);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		union.add(trackSegment);
		double t = 0.5*(TURN_DIAMETER+TRACK_WIDTH)+5;
		union.add(csg.csg2D().construct2D().translate2D(t,0).add(endHold(csg)));
		Module2D leftEndHold = csg.csg2D().construct2D().mirror2D(0,1).add(endHold(csg));
		leftEndHold = csg.csg2D().construct2D().translate2D(t, 0).add(leftEndHold);
		leftEndHold = csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/16)).add(leftEndHold);
		union.add(leftEndHold);
		Module2D midHold = csg.csg2D().construct2D().translate2D(t, 0).add(midHold(csg));
		midHold = csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(midHold);
		union.add(midHold);
		Module2DFrom2D diff = csg.csg2D().construct2D().difference2D().add(union);
		Module2D hole = csg.csg2D().shapes2D().circle2D(3, 32);
		Module2D h1 = csg.csg2D().construct2D().translate2D(t+5, 5).add(hole);
		Module2D h2 = csg.csg2D().construct2D().translate2D(t+5, -5).add(hole);
		diff.add(h1);
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/16)).add(h2));
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(h1));
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(h2));
		return diff;
	}

	public Module2D getInnerCurvedTrack(CSG csg)
	{
		Module2D outerCircle = csg.csg2D().shapes2D().circle2D(TURN_DIAMETER-TRACK_WIDTH, 1024);
		Module2D innerCircle = csg.csg2D().shapes2D().circle2D(TURN_DIAMETER-TRACK_WIDTH-20, 1024);
		Module2D cutPie = csg.csg2D().shapes2D().pie2D(TURN_DIAMETER, Angle.ZERO, Angle.rotations(1.0/16), 16);
		Module2D allTrack = csg.csg2D().construct2D().difference2D().add(outerCircle).add(innerCircle);
		Module2D trackSegment = csg.csg2D().construct2D().intersection2D().add(allTrack).add(cutPie);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		union.add(trackSegment);
		double t = 0.5*(TURN_DIAMETER - TRACK_WIDTH) - 15;
		union.add(csg.csg2D().construct2D().translate2D(t,0).add(endHold(csg)));
		Module2D leftEndHold = csg.csg2D().construct2D().mirror2D(0,1).add(endHold(csg));
		leftEndHold = csg.csg2D().construct2D().translate2D(t, 0).add(leftEndHold);
		leftEndHold = csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/16)).add(leftEndHold);
		union.add(leftEndHold);
		Module2D midHold = csg.csg2D().construct2D().translate2D(t, 0).add(midHold(csg));
		midHold = csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(midHold);
		union.add(midHold);
		Module2DFrom2D diff = csg.csg2D().construct2D().difference2D().add(union);
		Module2D hole = csg.csg2D().shapes2D().circle2D(3, 32);
		Module2D h1 = csg.csg2D().construct2D().translate2D(t+5, 5).add(hole);
		Module2D h2 = csg.csg2D().construct2D().translate2D(t+5, -5).add(hole);
		diff.add(h1);
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/16)).add(h2));
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(h1));
		diff.add(csg.csg2D().construct2D().rotate2D(Angle.rotations(1.0/32)).add(h2));
		return diff;
	}

	private Module2D endHold(CSG csg)
	{
		Module2D rect = csg.csg2D().shapes2D().rect2D(5,10);
		Module2D circle = csg.csg2D().shapes2D().circle2D(10, 64);
		rect = csg.csg2D().construct2D().translate2D(-2.5,5).add(rect);
		circle = csg.csg2D().construct2D().translate2D(-5,5).add(circle);
		Module2D union = csg.csg2D().construct2D().union2D().add(rect).add(circle);
		return csg.csg2D().construct2D().rotate2D(Angle.rotations(-0.25)).add(union);
	}

	private Module2D midHold(CSG csg)
	{
		Module2D circle = csg.csg2D().shapes2D().circle2D(10, 64);
		Module2D c1 = csg.csg2D().construct2D().translate2D(5,5).add(circle);
		Module2D c2 = csg.csg2D().construct2D().translate2D(5,-5).add(circle);
		Module2DFrom2D hull =csg.csg2D().construct2D().hull2D();
		hull.add(c1).add(c2);
		return hull;
	}
}
