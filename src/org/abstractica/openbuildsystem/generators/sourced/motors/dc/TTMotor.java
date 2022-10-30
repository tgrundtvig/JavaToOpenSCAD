package org.abstractica.openbuildsystem.generators.sourced.motors.dc;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.openbuildsystem.Print3DAdjust;

public class TTMotor
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private static final double CORNER_D = 8;
	private static final double WIDTH = 18.7;
	private static final double HEIGHT = 22.3;
	private static final double AXLE_TO_FRONT = 11.2;
	private static final double AXLE_TO_BACK = 26.1;
	private static final double MOTOR_HOLDER_LENGTH = 36;
	private static final double MOTOR_SIDE_ROOM = 3;
	private static final double AXLE_TO_SIDE_KNOB = 11;
	private static final double SIDE_KNOB_HEIGHT = 2;
	private static final double SIDE_KNOB_DIAMETER = 5;


	private final boolean doubleAxle;

	public TTMotor(boolean doubleAxle, CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
		this.doubleAxle = doubleAxle;
	}

	public Module3D getAxleCutout()
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(5.35+2*adjust.holeRoundTight().xy(),
				8, 1024);
		Module3D box = csg.csg3D().shapes3D().box3D(3.8+2*adjust.holeSquareTight().xy(), 6, 10);
		Module3D axleCutout = csg.csg3D().construct3D().intersection3D().add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D(0,0,3.999).add(axleCutout);
	}

	public Module3D getAxle()
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(5.35+2*adjust.solidRoundTight().xy(),
				7.8, 1024);
		Module3D box = csg.csg3D().shapes3D().box3D(3.8+2*adjust.solidSquareTight().xy(), 6, 10);
		Module3D axleCutout = csg.csg3D().construct3D().intersection3D().add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D(0,0,3.999).add(axleCutout);
	}

	public Module3D getCutout()
	{
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add(gearBox());
		res.add(gearFrontKnob());
		res.add(gearSideKnob());
		res.add(axle());
		res.add(motorCylinder());
		res.add(motorSideRoom());
		return res;
	}

	private Module3D gearBox()
	{
		double adjXY = adjust.holeSquareTight().xy();
		double adjZ = adjust.holeSquareTight().z();
		Shapes3D s3D = csg.csg3D().shapes3D();
		Construct3D c3D = csg.csg3D().construct3D();
		Module3D corner = s3D.cylinder3D(CORNER_D, WIDTH+2*adjXY,64);
		corner = c3D.rotateY(Angle.degrees(90)).add(corner);

		double cornerTY = AXLE_TO_FRONT-0.5*CORNER_D+adjXY;
		double cornerTZ = 0.5*(HEIGHT-CORNER_D)+adjZ;

		Module3D tCA = c3D.translate3D(0, cornerTY, cornerTZ).add(corner);
		Module3D tCB = c3D.translate3D(0, cornerTY, -cornerTZ).add(corner);

		Module3D back = s3D.box3D(WIDTH + 2*adjXY, 2, HEIGHT+2*adjZ);
		double backTY = -AXLE_TO_BACK+1-adjXY;
		Module3D tB = c3D.translate3D(0, backTY, 0).add(back);
		return c3D.hull3D().add(tCA).add(tCB).add(tB);
	}

	private Module3D axle()
	{
		Module3D cyl;
		if(doubleAxle)
		{
			cyl = csg.csg3D().shapes3D().cylinder3D(6, 40, 128);
		}
		else
		{
			cyl = csg.csg3D().construct3D().translate3D(0,0,10).add
					(
						csg.csg3D().shapes3D().cylinder3D(6, 20, 128)
					);
		}
		return csg.csg3D().construct3D().rotateY(Angle.degrees(90)).add(cyl);
	}

	private Module3D motorCylinder()
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(
				HEIGHT+2*adjust.holeSquareTight().z(),
				MOTOR_HOLDER_LENGTH, 128);
		cyl = csg.csg3D().construct3D().rotateX(Angle.degrees(90)).add(cyl);
		Module3D box = csg.csg3D().shapes3D().box3D(
				WIDTH + 2*adjust.holeSquareTight().xy(),
				MOTOR_HOLDER_LENGTH,
				HEIGHT + 2* adjust.holeSquareTight().z());
		Module3DFrom3D intersect = csg.csg3D().construct3D().intersection3D();
		intersect.add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D
				(
					0,
					-0.5*MOTOR_HOLDER_LENGTH-AXLE_TO_BACK+0.001,
					0
				).add(intersect);
	}

	private Module3D motorSideRoom()
	{
		Module3D box = csg.csg3D().shapes3D().box3D
				(
					WIDTH + 2*adjust.holeSquareTight().xy() + 2*MOTOR_SIDE_ROOM,
					MOTOR_HOLDER_LENGTH-3,
					14
				);
		return csg.csg3D().construct3D().translate3D(
				0,
				-0.5*(MOTOR_HOLDER_LENGTH-3)-AXLE_TO_BACK-3,
				0).add(box);
	}

	private Module3D gearFrontKnob()
	{
		return csg.csg3D().construct3D().translate3D
				(
						0,
						0.5*6.5-1+AXLE_TO_FRONT,
						0
				).add(csg.csg3D().shapes3D().box3D(5,6.5,6.5));
	}

	private Module3D gearSideKnob()
	{
		return csg.csg3D().construct3D().translate3D
				(
						0.5*(WIDTH+SIDE_KNOB_HEIGHT+1)-1,
						-AXLE_TO_SIDE_KNOB,
						0
				).add
				(
					csg.csg3D().construct3D().rotateY(Angle.degrees(90)).add
							(
									csg.csg3D().shapes3D().cylinder3D
											(
													SIDE_KNOB_DIAMETER,
													SIDE_KNOB_HEIGHT + 1,
													32
											)
							)
				);
	}
}
