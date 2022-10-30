package org.abstractica.openbuildsystem.generators.trainsystem.rollingstock;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.math.Polar2D;
import org.abstractica.javatoopenscad.csg.math.Vector2D;
import org.abstractica.openbuildsystem.generators.axles.CrossAxles;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.sourced.motors.dc.TTMotor;

import java.util.ArrayList;
import java.util.List;

public class TrainWheels
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private final double wheelAvgDiameter = 40;
	private final double wheelFlangeHeight = 2;
	private final double wheelWidth = 8;
	private final double cutRingHeight = 3;
	private final Angle wheelAngle = Angle.degrees(5);
	private final double axleBase = 3;
	private final double axleBaseDiameter = 12;
	private final double crossAxleLength = wheelWidth + wheelFlangeHeight - cutRingHeight;
	private final double axleDiameter = 8.1;

	public TrainWheels(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}


	public Module3D shortMotorAxle()
	{
		return trainAxle(8, false);
	}

	public Module3D longMotorAxle()
	{
		return trainAxle(32, false);
	}

	public Module3D shortFreeAxle()
	{
		return trainAxle(27.4, true);
	}

	public Module3D longFreeAxle()
	{
		return trainAxle(33.4, false);
	}

	public Module3D trainWheel()
	{
		double diameterHalfDiff = Angle.sin(wheelAngle) * wheelWidth;

		Module3D flange = csg.csg3D().shapes3D().cylinder3D(wheelAvgDiameter+diameterHalfDiff+8,
				wheelFlangeHeight, 1024);
		flange = csg.csg3D().construct3D().translate3D(0,0,0.5*wheelFlangeHeight).add(flange);
		Module3D base = csg.csg3D().shapes3D().cone3D(wheelAvgDiameter + diameterHalfDiff,
				wheelAvgDiameter - diameterHalfDiff, wheelWidth, 1024);
		base = csg.csg3D().construct3D().translate3D(0,0, 0.5*wheelWidth + wheelFlangeHeight).add(base);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D().add(flange).add(base);
		CrossAxles ca = new CrossAxles(csg, adjust);
		double h = wheelFlangeHeight + wheelWidth + 2;
		Module3D crossAxleHole = ca.axleCutout(axleBaseDiameter, h);
		crossAxleHole = csg.csg3D().construct3D().translate3D(0,0, 0.5*h - 1).add(crossAxleHole);
		//Module3D ring = csg.csg3D().shapes3D().hollowCylinder3D(axleBaseDiameter + 4,
				//wheelAvgDiameter - diameterHalfDiff - 4, cutRingHeight+1, 1024);
		Module3D ring = csg.csg3D().shapes3D().cylinder3D(wheelAvgDiameter - diameterHalfDiff - 4,
				cutRingHeight+1, 1024);
		ring = csg.csg3D().construct3D().translate3D(0,0,
				0.5*(cutRingHeight+1) + wheelFlangeHeight + wheelWidth - cutRingHeight).add(ring);
		return csg.csg3D().construct3D().difference3D().add(union).add(crossAxleHole).add(ring);
	}

	public Module3D trainAxle(double length, boolean male)
	{
		TTMotor m = new TTMotor(true, csg, adjust);
		CrossAxles ca = new CrossAxles(csg, adjust);
		Module3D axle = csg.csg3D().shapes3D().cylinder3D(axleDiameter + 2*adjust.solidRoundTight().xy(),
				length, 1024);
		axle = csg.csg3D().construct3D().translate3D(0,0,0.5*length + axleBase + crossAxleLength).add(axle);
		Module3D base = csg.csg3D().shapes3D().cylinder3D(axleBaseDiameter + 2*adjust.solidRoundTight().xy(),
				axleBase, 1024);
		base = csg.csg3D().construct3D().translate3D(0,0,0.5*axleBase + crossAxleLength).add(base);
		Module3D crossAxle = ca.axle(axleBaseDiameter, crossAxleLength,true, false);
		crossAxle = csg.csg3D().construct3D().translate3D(0,0,0.5*crossAxleLength).add(crossAxle);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D().add(crossAxle).add(base).add(axle);
		if(male)
		{
			Module3D axleTap = m.getAxle();
			axleTap = csg.csg3D().construct3D().translate3D(0,0,crossAxleLength + axleBase + length).add(axleTap);
			union.add(axleTap);
			return union;
		}
		else
		{
			Module3D axleHole = m.getAxleCutout();
			axleHole = csg.csg3D().construct3D().rotateX(Angle.degrees(180)).add(axleHole);
			axleHole = csg.csg3D().construct3D().translate3D(0,0,
					crossAxleLength + axleBase + length).add(axleHole);
			return csg.csg3D().construct3D().difference3D().add(union).add(axleHole);
		}
	}

	public Module3D sawWheel()
	{
		int teeth = 80;
		double toothHeight = 1;
		double toothWidth = 2;
		double circumfence = toothWidth * teeth;
		double avgRadius = circumfence / (2*Math.PI);

		Module3DFrom3D flange = csg.csg3D().construct3D().union3D();
		flange.add(csg.csg3D().construct3D().translate3D(0,0,0.5)
				.add(csg.csg3D().shapes3D().cylinder3D(avgRadius*2+9,
				1, 1024)));
		flange.add(csg.csg3D().construct3D().translate3D(0,0,0.5*(wheelFlangeHeight-1) + 1)
				.add(csg.csg3D().shapes3D().cone3D(
						avgRadius*2+9,
						avgRadius*2+1,
						wheelFlangeHeight-1, 1024)));
		//flange = csg.csg3D().construct3D().translate3D(0,0,0.5*wheelFlangeHeight).add(flange);
		Module3D base = csg.csg3D().construct3D().linearExtrude(wheelWidth, 1.0, 8)
				.add(sawWheelProfile(toothHeight, toothWidth, teeth));
		base = csg.csg3D().construct3D().translate3D(0,0, 0.5*wheelWidth + wheelFlangeHeight).add(base);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D().add(flange).add(base);
		CrossAxles ca = new CrossAxles(csg, adjust);
		double h = wheelFlangeHeight + wheelWidth + 2;
		Module3D crossAxleHole = ca.axleCutout(axleBaseDiameter, h);
		crossAxleHole = csg.csg3D().construct3D().translate3D(0,0, 0.5*h - 1).add(crossAxleHole);
		//Module3D ring = csg.csg3D().shapes3D().hollowCylinder3D(axleBaseDiameter + 4,
		//wheelAvgDiameter - diameterHalfDiff - 4, cutRingHeight+1, 1024);
		Module3D ring = csg.csg3D().shapes3D().cylinder3D(avgRadius*2 - 4,
				cutRingHeight+1, 1024);
		ring = csg.csg3D().construct3D().translate3D(0,0,
				0.5*(cutRingHeight+1) + wheelFlangeHeight + wheelWidth - cutRingHeight).add(ring);
		return csg.csg3D().construct3D().difference3D().add(union).add(crossAxleHole).add(ring);
	}

	public Module2D sawWheelProfile(double toothHeight, double toothWidth, int teeth)
	{
		double circumfence = toothWidth * teeth;
		double avgRadius = circumfence / (2*Math.PI);

		List<Vector2D> points = new ArrayList<>();
		double d = 1.0 / (2*teeth);
		for(int i = 0; i < 2*teeth; ++i)
		{
			Angle angle = Angle.rotations(i*d);
			double r = (i % 2 == 0) ? avgRadius + 0.5*toothHeight : avgRadius - 0.5*toothHeight;
			Polar2D p = Polar2D.create(r, angle);
			points.add(p.asVector2D());
		}
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
