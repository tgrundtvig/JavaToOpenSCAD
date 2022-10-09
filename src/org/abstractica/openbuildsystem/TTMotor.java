package org.abstractica.openbuildsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;

public class TTMotor
{
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

	public TTMotor(boolean doubleAxle)
	{
		this.doubleAxle = doubleAxle;
	}

	public Module3D getAxleCutout(CSG csg, Adjust adjust)
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(5.35+2*adjust.getXYHoleAdjust(),
				8, 1024);
		Module3D box = csg.csg3D().shapes3D().box3D(3.8+2*adjust.getXYHoleAdjust(), 6, 10);
		Module3D axleCutout = csg.csg3D().construct3D().intersection3D().add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D(0,0,3.999).add(axleCutout);
	}

	public Module3D getAxle(CSG csg, Adjust adjust)
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(5.35+2*adjust.getXYAdjust(),
				7.8, 1024);
		Module3D box = csg.csg3D().shapes3D().box3D(3.8+2*adjust.getXYAdjust(), 6, 10);
		Module3D axleCutout = csg.csg3D().construct3D().intersection3D().add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D(0,0,3.999).add(axleCutout);
	}

	public Module3D getCutout(CSG csg, Adjust adjust)
	{
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add(gearBox(csg, adjust));
		res.add(gearFrontKnob(csg, adjust));
		res.add(gearSideKnob(csg, adjust));
		res.add(axle(csg,adjust));
		res.add(motorCylinder(csg, adjust));
		res.add(motorSideRoom(csg, adjust));
		return res;
	}

	private Module3D gearBox(CSG csg, Adjust adjust)
	{
		Shapes3D s3D = csg.csg3D().shapes3D();
		Construct3D c3D = csg.csg3D().construct3D();
		Module3D corner = s3D.cylinder3D(CORNER_D, WIDTH+2*adjust.getXYHoleAdjust(),64);
		corner = c3D.rotate3D(Angle.ZERO, Angle.degrees(90), Angle.ZERO).add(corner);

		double cornerTY = AXLE_TO_FRONT-0.5*CORNER_D+adjust.getXYHoleAdjust();
		double cornerTZ = 0.5*(HEIGHT-CORNER_D)+adjust.getZHoleAdjust();

		Module3D tCA = c3D.translate3D(0, cornerTY, cornerTZ).add(corner);
		Module3D tCB = c3D.translate3D(0, cornerTY, -cornerTZ).add(corner);

		Module3D back = s3D.box3D(WIDTH + 2* adjust.getXYHoleAdjust(), 2, HEIGHT+2* adjust.getZHoleAdjust());
		double backTY = -AXLE_TO_BACK+1- adjust.getXYHoleAdjust();
		Module3D tB = c3D.translate3D(0, backTY, 0).add(back);
		return c3D.hull3D().add(tCA).add(tCB).add(tB);
	}

	private Module3D axle(CSG csg, Adjust adjust)
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
		return csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.degrees(90), Angle.ZERO).add(cyl);
	}

	private Module3D motorCylinder(CSG csg, Adjust adjust)
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(
				HEIGHT+2* adjust.getZHoleAdjust(),
				MOTOR_HOLDER_LENGTH, 128);
		cyl = csg.csg3D().construct3D().rotate3D(Angle.degrees(90), Angle.ZERO, Angle.ZERO).add(cyl);
		Module3D box = csg.csg3D().shapes3D().box3D(
				WIDTH + 2* adjust.getXYHoleAdjust(),
				MOTOR_HOLDER_LENGTH,
				HEIGHT + 2* adjust.getZHoleAdjust());
		Module3DFrom3D intersect = csg.csg3D().construct3D().intersection3D();
		intersect.add(cyl).add(box);
		return csg.csg3D().construct3D().translate3D
				(
					0,
					-0.5*MOTOR_HOLDER_LENGTH-AXLE_TO_BACK+0.001,
					0
				).add(intersect);
	}

	private Module3D motorSideRoom(CSG csg, Adjust adjust)
	{
		Module3D box = csg.csg3D().shapes3D().box3D
				(
					WIDTH + 2* adjust.getXYHoleAdjust() + 2*MOTOR_SIDE_ROOM,
					MOTOR_HOLDER_LENGTH-3,
					14
				);
		return csg.csg3D().construct3D().translate3D(
				0,
				-0.5*(MOTOR_HOLDER_LENGTH-3)-AXLE_TO_BACK-3,
				0).add(box);
	}

	private Module3D gearFrontKnob(CSG csg, Adjust adjust)
	{
		return csg.csg3D().construct3D().translate3D
				(
						0,
						0.5*6.5-1+AXLE_TO_FRONT,
						0
				).add(csg.csg3D().shapes3D().box3D(5,6.5,6.5));
	}

	private Module3D gearSideKnob(CSG csg, Adjust adjust)
	{
		return csg.csg3D().construct3D().translate3D
				(
						0.5*(WIDTH+SIDE_KNOB_HEIGHT+1)-1,
						-AXLE_TO_SIDE_KNOB,
						0
				).add
				(
					csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.degrees(90), Angle.ZERO).add
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
