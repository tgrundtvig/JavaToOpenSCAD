package org.abstractica.openbuildsystem.generators.trainsystem.rollingstock;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.openbuildsystem.*;
import org.abstractica.openbuildsystem.generators.sourced.microswitches.MicroSwitchRoller;
import org.abstractica.openbuildsystem.generators.sourced.motors.dc.TTMotor;
import org.abstractica.openbuildsystem.unused.ClickSystemOld;

import java.util.ArrayList;
import java.util.List;

public class TrainBogie
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private static final double WIDTH = 65;
	private static final double LENGTH = 130;
	private static final double BOTTOM_HEIGHT = 12.5;
	private static final double TOP_HEIGHT = 12.5;
	private static final double TOTAL_HEIGHT = BOTTOM_HEIGHT + TOP_HEIGHT;
	private static final double BUMPER_PLATE_WALL_THICKNESS = 3;
	private static final double SCREW_HOLE_DIAMETER = 3.4;
	private static final double SCREW_X_DIST = 50;
	private static final double SCREW_Y_DIST = 110;


	private static final double MS_HOLDER_LENGTH = 50;
	private static final double MS_HOLDER_TAP_WIDTH = 2;
	private static final double MS_HOLDER_TAP_HEIGHT = 10;
	private static final double MS_HOLDER_TAP_IN_TOP = 5;
	private static final double MS_HOLDER_TOP = 2;
	private static final double MS_HOLDER_BOTTOM = 4;
	private static final double MS_HOLDER_SWITCH_HEIGHT = 6-MS_HOLDER_BOTTOM;
	private static final double MS_HOLDER_HEIGHT_ADJUST = 0;

	private static final double MP_HEIGHT = 10;
	private static final double MP_MOUNT_THICKNESS = 7;
	private static final double MP_SPACER_HEIGHT = 10;
	private static final double MP_SPACER_WIDTH = 5;

	public TrainBogie(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}

	public Module3D mountPlate()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Shapes3D s3d = csg.csg3D().shapes3D();

		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D hole = c3d.union3D();

		Module3D plate = c3d.translate3D(0,0,0.5*MP_HEIGHT).add(s3d.box3D(WIDTH, LENGTH, MP_HEIGHT));
		solid.add(plate);

		//Screw holes
		Module3D screwHole = s3d.cylinder3D(SCREW_HOLE_DIAMETER, MP_HEIGHT+2, 64);
		hole.add(c3d.translate3D(-0.5*SCREW_X_DIST, 0.5*SCREW_Y_DIST, 0.5*(MP_HEIGHT+2)).add(screwHole));
		hole.add(c3d.translate3D(0.5*SCREW_X_DIST, 0.5*SCREW_Y_DIST, 0.5*(MP_HEIGHT+2)).add(screwHole));
		hole.add(c3d.translate3D(-0.5*SCREW_X_DIST, -0.5*SCREW_Y_DIST, 0.5*(MP_HEIGHT+2)).add(screwHole));
		hole.add(c3d.translate3D(0.5*SCREW_X_DIST, -0.5*SCREW_Y_DIST, 0.5*(MP_HEIGHT+2)).add(screwHole));

		Module3D cyl1 = c3d.translate3D(0,0,0.5*MP_SPACER_HEIGHT + MP_HEIGHT)
				.add(s3d.cylinder3D(WIDTH, MP_SPACER_HEIGHT, 1024));
		Module3D cyl2 = c3d.translate3D(0,0,0.5*(MP_MOUNT_THICKNESS+MP_SPACER_HEIGHT) + MP_HEIGHT)
				.add(s3d.cylinder3D(WIDTH-2*MP_SPACER_WIDTH,
						MP_MOUNT_THICKNESS+MP_SPACER_HEIGHT,
						1024));
		solid.add(cyl1);
		solid.add(cyl2);
		//Wire hole
		Module3D wireHole = s3d.cylinder3D(12, 100, 32);
		hole.add(wireHole);
		Module3DFrom3D diff = c3d.difference3D().add(solid).add(hole);
		//Clicker holes
		ClickSystemOld cs = new ClickSystemOld();
		Module3D clickCutout = cs.clickerCutout(true, csg, adjust);
		clickCutout = c3d.translate3D(0.5*WIDTH-MP_SPACER_WIDTH-7.5, 0, MP_HEIGHT+MP_MOUNT_THICKNESS+MP_SPACER_HEIGHT)
				.add(clickCutout);
		for(int i = 0; i < 8; ++i)
		{
			hole.add(c3d.rotateZ(Angle.rotations((1.0 / 8)*i)).add(clickCutout));
		}
		return diff;
	}

	public Module3D mountLock()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Shapes3D s3d = csg.csg3D().shapes3D();

		Module3DFrom3D solid = c3d.union3D();
		Module3DFrom3D hole = c3d.union3D();

		Module3D plate = s3d.cylinder3D(WIDTH, 5, 1024);
		plate = c3d.translate3D(0,0,0.5*5).add(plate);
		solid.add(plate);
		//Wire hole
		Module3D wireHole = s3d.cylinder3D(12, 50, 32);
		hole.add(wireHole);
		Module3DFrom3D diff = c3d.difference3D().add(solid).add(hole);
		//Clicker holes
		ClickSystemOld cs = new ClickSystemOld();
		Module3D clickCutout = cs.clickerCutout(true, csg, adjust);
		clickCutout = csg.csg3D().construct3D().translate3D(0.5*WIDTH-MP_SPACER_WIDTH-7.5, 0, 0).add(clickCutout);
		for(int i = 0; i < 8; ++i)
		{
			hole.add(c3d.rotateZ(Angle.rotations((1.0 / 8)*i)).add(clickCutout));
		}
		return diff;
	}



	public Module3D getDoubleMotorBogieBottomPrint()
	{
		Module3DFrom3D bottom = csg.csg3D().construct3D().intersection3D();
		bottom.add(bottomIntersect());
		bottom.add(doubleMotorBogie());
		return csg.csg3D().construct3D().union3D().add(bottom).add(taps());
	}

	public Module3D getDoubleMotorBogieTopPrint()
	{
		Module3DFrom3D top = csg.csg3D().construct3D().intersection3D();
		top.add(topIntersect());
		top.add(doubleMotorBogie());
		top = csg.csg3D().construct3D().rotateY(Angle.degrees(180)).add(top);
		return csg.csg3D().construct3D().difference3D().add(top).add(tapHoles());
	}

	public Module3D getMicroSwitchBogieBottomPrint()
	{
		Module3DFrom3D bottom = csg.csg3D().construct3D().intersection3D();
		bottom.add(bottomIntersect());
		bottom.add(microSwitchBogie());
		return csg.csg3D().construct3D().union3D().add(bottom).add(taps());
	}

	public Module3D getMicroSwitchBogieTopPrint()
	{
		Module3DFrom3D top = csg.csg3D().construct3D().intersection3D();
		top.add(topIntersect());
		top.add(microSwitchBogie());
		top = csg.csg3D().construct3D().rotateY(Angle.degrees(180)).add(top);
		return csg.csg3D().construct3D().difference3D().add(top).add(tapHoles());
	}

	public Module3D getFreeRollBottomPrint()
	{
		Module3DFrom3D bottom = csg.csg3D().construct3D().intersection3D();
		bottom.add(bottomIntersect());
		bottom.add(templateBogie());
		return csg.csg3D().construct3D().union3D().add(bottom).add(taps());
	}

	public Module3D getFreeRollTopPrint()
	{
		Module3DFrom3D top = csg.csg3D().construct3D().intersection3D();
		top.add(topIntersect());
		top.add(templateBogie());
		top = csg.csg3D().construct3D().rotateY(Angle.degrees(180)).add(top);
		return csg.csg3D().construct3D().difference3D().add(top).add(tapHoles());
	}

	private Module3D microSwitchBogie()
	{
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D();
		diff.add(templateBogie());
		diff.add(microSwitchCutout());
		return diff;
	}

	public Module3D microSwitchCutout()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3DFrom3D union = c3d.union3D();
		Module3D holder = microSwitchHolderCutout();
		union.add(c3d.translate3D(-15, 0,0).add(holder));
		union.add(holder);
		union.add(c3d.translate3D(15, 0,0).add(holder));
		double height = TOP_HEIGHT-1;
		Module3D box = csg.csg3D().shapes3D().box3D(
				40+2*adjust.holeSquareTight().xy(),
				MS_HOLDER_LENGTH+2*adjust.holeSquareTight().xy(),
				height
				);
		box = c3d.translate3D(0,0, 0.5*height-0.001).add(box);
		union.add(box);
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(8, TOP_HEIGHT, 64);
		cyl = c3d.translate3D(0,0, 0.5*TOP_HEIGHT + 0.5*MS_HOLDER_TAP_HEIGHT + MS_HOLDER_TOP+1).add(cyl);
		union.add(cyl);
		return union;
	}

	public Module3D microSwitchHolderCutout()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module2D profile = microSwitchHolderProfile(true);
		Module3D profile3D = c3d.linearExtrude(10+2*adjust.holeSquareTight().xy(), 4).add(profile);
		profile3D = c3d.rotate3D(Angle.degrees(90), Angle.ZERO, Angle.degrees(90)).add(profile3D);
		profile3D = c3d.translate3D(0,0,-MS_HOLDER_TAP_HEIGHT+MS_HOLDER_TAP_IN_TOP).add(profile3D);
		return profile3D;
	}

	public Module3D microSwitchHolderSingle()
	{
		MicroSwitchRoller msr = new MicroSwitchRoller(csg, adjust);
		Module3DFrom3D res = csg.csg3D().construct3D().difference3D();
		res.add(microSwitchHolderPlate());
		Module3D msrCutout = msr.microSwitchRollerCutout(TOTAL_HEIGHT, TOTAL_HEIGHT);
		res.add(csg.csg3D().construct3D().translate3D(0, MS_HOLDER_SWITCH_HEIGHT, 0).add(msrCutout));
		res = csg.csg3D().construct3D().translate3D(0,-MS_HOLDER_TAP_HEIGHT+MS_HOLDER_TAP_IN_TOP, 0).add(res);
		return res;
	}

	public Module3D microSwitchHolderDouble()
	{
		MicroSwitchRoller msr = new MicroSwitchRoller(csg, adjust);
		Module3DFrom3D res = csg.csg3D().construct3D().difference3D();
		res.add(microSwitchHolderPlate());
		Module3D msrCutout = msr.microSwitchRollerCutout(TOTAL_HEIGHT, TOTAL_HEIGHT);
		res.add(csg.csg3D().construct3D().translate3D(-11.5, MS_HOLDER_SWITCH_HEIGHT, 0).add(msrCutout));
		res.add(csg.csg3D().construct3D().translate3D(11.5, MS_HOLDER_SWITCH_HEIGHT, 0).add(msrCutout));
		res = csg.csg3D().construct3D().translate3D(0,-MS_HOLDER_TAP_HEIGHT+MS_HOLDER_TAP_IN_TOP, 0).add(res);
		return res;
	}

	private Module3D microSwitchHolderPlate()
	{
		Module3D plate = csg.csg3D().construct3D().linearExtrude(5, 4)
				.add(microSwitchHolderProfile(false));
		return csg.csg3D().construct3D().translate3D(0,0,-2.5).add(plate);
	}

	private Module2D microSwitchHolderProfile(boolean cutout)
	{
		double adj = cutout ? adjust.holeSquareTight().xy() : adjust.solidSquareTight().xy();
		double bottom = cutout ? TOTAL_HEIGHT : MS_HOLDER_BOTTOM;
		double height_adj = cutout ? 0 : MS_HOLDER_HEIGHT_ADJUST;
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,-bottom-adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+adj,-bottom-adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+adj,-adj-height_adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+MS_HOLDER_TAP_WIDTH+adj,-adj-height_adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+MS_HOLDER_TAP_WIDTH+adj,
				MS_HOLDER_TAP_HEIGHT+adj-height_adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+adj,MS_HOLDER_TAP_HEIGHT+adj-height_adj));
		points.add(Vector2D.create(0.5*MS_HOLDER_LENGTH+adj, MS_HOLDER_TAP_HEIGHT+MS_HOLDER_TOP+adj-height_adj));
		points.add(Vector2D.create(0,MS_HOLDER_TAP_HEIGHT+MS_HOLDER_TOP+adj-height_adj));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D profile = csg.csg2D().construct2D().union2D();
		profile.add(half);
		profile.add(csg.csg2D().construct2D().mirror2D(1, 0).add(half));
		return profile;
	}

	private Module3D doubleMotorBogie()
	{
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D();
		diff.add(templateBogie());
		diff.add(doubleMotorCutout());
		return diff;
	}

	private Module3D doubleMotorCutout()
	{
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		Module3D half = halfMotorCutout();
		res.add(half);
		res.add(csg.csg3D().construct3D().rotateZ(Angle.degrees(180)).add(half));
		//Wire hole
		double wireHoleHeight = TOP_HEIGHT - 5.5;
		res.add
		(
			csg.csg3D().construct3D().translate3D(0,0,0.5*wireHoleHeight + 6).add
			(
				csg.csg3D().shapes3D().cone3D(20, 6, wireHoleHeight, 64)
			)
		);
		return res;
	}

	private Module3D halfMotorCutout()
	{
		TTMotor motor = new TTMotor(true, csg, adjust);
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add
		(
			csg.csg3D().construct3D().translate3D(-12, 0.5*LENGTH-25, 0).add
			(
				csg.csg3D().construct3D().rotateY(Angle.degrees(180)).add(motor.getCutout())
			)
		);
		//Air holes
		for(int y = 8; y >= -20; y -= 4)
		{
			for(int z = 10; z >= -10; z -= 4)
			{
				res.add
				(
					csg.csg3D().construct3D().translate3D(-12-0.5*25, y+1, z).add
					(
						csg.csg3D().construct3D().rotateY(Angle.degrees(-90)).add
						(
							csg.csg3D().shapes3D().cylinder3D(2.5, 25, 32)
						)
					)
				);
			}
		}
		return res;
	}

	private Module3D templateBogie()
	{
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D();
		diff.add(bogieBox());
		diff.add(templateBogieCutout());
		return diff;
	}

	private Module3D templateBogieCutout()
	{
		Module3DFrom3D half = csg.csg3D().construct3D().union3D();
		//Screw holes
		Module3D screwHole = csg.csg3D().shapes3D().cylinder3D(SCREW_HOLE_DIAMETER, TOTAL_HEIGHT + 2, 32);
		screwHole = csg.csg3D().construct3D().translate3D(0,0,0.5*(TOTAL_HEIGHT + 2)-BOTTOM_HEIGHT-1).add(screwHole);
		half.add(csg.csg3D().construct3D().translate3D(-0.5*SCREW_X_DIST, 0.5*SCREW_Y_DIST, 0).add(screwHole));
		half.add(csg.csg3D().construct3D().translate3D(0.5*SCREW_X_DIST, 0.5*SCREW_Y_DIST, 0).add(screwHole));
		//Axle cutout
		half.add
		(
			csg.csg3D().construct3D().translate3D(0,0.5*LENGTH-25,0).add
			(
				csg.csg3D().construct3D().rotateY(Angle.degrees(90)).add
				(
						csg.csg3D().shapes3D().cylinder3D(14, WIDTH+2, 128)
				)
			)
		);
		//BB's
		Module3D bb = bb();
		//Corner BB's
		half.add
		(
			csg.csg3D().construct3D().translate3D(-0.5*WIDTH+5.5, 0.5*LENGTH-25, 0).add(bb)
		);
		half.add
		(
			csg.csg3D().construct3D().translate3D(0.5*WIDTH-5.5, 0.5*LENGTH-25, 0).add(bb)
		);
		//Middle BB
		half.add
		(
			csg.csg3D().construct3D().translate3D(3, 0.5*LENGTH-25, 0).add(bb)
		);
		//Bumper plate
		half.add(csg.csg3D().construct3D().translate3D(0, 0.5*LENGTH, 0).add(bumperPlateCutout()));
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add(half);
		res.add(csg.csg3D().construct3D().rotateZ(Angle.degrees(180)).add(half));
		return res;
	}

	private Module3D tapHoles()
	{
		Module3DFrom3D half = csg.csg3D().construct3D().union3D();
		Module3D tapHole = csg.csg3D().shapes3D().cylinder3D
				(
						4 +  2 * adjust.holeRoundTight().xy(),
						3,
						64
				);
		half.add(csg.csg3D().construct3D().translate3D(27, 21, (-0.5*3)+0.2 ).add(tapHole));
		half.add(csg.csg3D().construct3D().translate3D(-15, 53, (-0.5*3)+0.2 ).add(tapHole));
		//half.add(csg.csg3D().construct3D().translate3D(-15, 29, (-0.5*3)+0.2 ).add(tapHole));
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add(half);
		res.add(csg.csg3D().construct3D().rotateZ(Angle.degrees(180)).add(half));
		return res;
	}

	private Module3D taps()
	{
		Module3DFrom3D half = csg.csg3D().construct3D().union3D();
		Module3D tap = csg.csg3D().shapes3D().cylinder3D
				(
						4 -  2 * adjust.solidRoundTight().xy(),
						3,
						64
				);
		half.add(csg.csg3D().construct3D().translate3D(-27, 21, (0.5*3)-1 ).add(tap));
		half.add(csg.csg3D().construct3D().translate3D(15, 53, (0.5*3)-1 ).add(tap));
		//half.add(csg.csg3D().construct3D().translate3D(15, 29, (0.5*3)-1 ).add(tap));
		Module3DFrom3D res = csg.csg3D().construct3D().union3D();
		res.add(half);
		res.add(csg.csg3D().construct3D().rotateZ(Angle.degrees(180)).add(half));
		return res;
	}

	private Module3D bumperPlateCutout()
	{
		double adjXY = adjust.holeSquareLoose().xy();
		double adjZ = adjust.holeSquareLoose().z();
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create
				(
						0.5*WIDTH-2*BUMPER_PLATE_WALL_THICKNESS+adjXY,
						-BOTTOM_HEIGHT-1
				)
		);
		points.add(Vector2D.create
				(
						0.5*WIDTH-2*BUMPER_PLATE_WALL_THICKNESS+adjXY,
						-BOTTOM_HEIGHT+BUMPER_PLATE_WALL_THICKNESS-adjust.holeSquareLoose().z()
				)
		);
		points.add(Vector2D.create
				(
						0.5*WIDTH-BUMPER_PLATE_WALL_THICKNESS+adjXY,
						-BOTTOM_HEIGHT+BUMPER_PLATE_WALL_THICKNESS-adjZ
				)
		);
		points.add(Vector2D.create
				(
						0.5*WIDTH-BUMPER_PLATE_WALL_THICKNESS+adjXY,
						TOP_HEIGHT+1
				)
		);
		points.add(Vector2D.create
				(
						-0.5*WIDTH+BUMPER_PLATE_WALL_THICKNESS-adjXY,
						TOP_HEIGHT+1
				)
		);
		points.add(Vector2D.create
				(
						-0.5*WIDTH+BUMPER_PLATE_WALL_THICKNESS-adjXY,
						-BOTTOM_HEIGHT+BUMPER_PLATE_WALL_THICKNESS-adjZ
				)
		);
		points.add(Vector2D.create
				(
						-0.5*WIDTH+2*BUMPER_PLATE_WALL_THICKNESS-adjXY,
						-BOTTOM_HEIGHT+BUMPER_PLATE_WALL_THICKNESS-adjZ
				)
		);
		points.add(Vector2D.create
				(
						-0.5*WIDTH+2*BUMPER_PLATE_WALL_THICKNESS-adjXY,
						-BOTTOM_HEIGHT-1
				)
		);

		Module3D plateBack = csg.csg3D().construct3D().translate3D(0,-1.5*BUMPER_PLATE_WALL_THICKNESS, 0).add
		(
			csg.csg3D().construct3D().rotateX(Angle.degrees(90)).add
			(
				csg.csg3D().construct3D().linearExtrude
				(
						BUMPER_PLATE_WALL_THICKNESS+2* adjXY,
						4
				).add
				(
					csg.csg2D().construct2D().polygon2D(points)
				)
			)
		);

		Module3D plateFront = csg.csg3D().construct3D()
				.translate3D(0, -0.5*BUMPER_PLATE_WALL_THICKNESS, 0.5*TOTAL_HEIGHT-BOTTOM_HEIGHT).add
		(
			csg.csg3D().shapes3D().box3D
			(
					WIDTH-4*BUMPER_PLATE_WALL_THICKNESS + 2*adjXY,
					BUMPER_PLATE_WALL_THICKNESS + 2*adjXY+1,
					TOTAL_HEIGHT + 2
			)
		);
		return csg.csg3D().construct3D().union3D().add(plateBack).add(plateFront);
	}

	private Module3D bogieBox()
	{
		Module3D box = csg.csg3D().shapes3D().box3D
		(
				WIDTH + 2*adjust.solidSquareTight().xy(),
				LENGTH + 2*adjust.solidSquareTight().xy(),
				TOTAL_HEIGHT + 2*adjust.solidSquareTight().z()
		);
		return csg.csg3D().construct3D().translate3D(0,0,0.5*TOTAL_HEIGHT-BOTTOM_HEIGHT).add(box);
	}

	private Module3D bottomIntersect()
	{
		return csg.csg3D().construct3D().translate3D(0,0,-0.5*(BOTTOM_HEIGHT + 2)).add
		(
			csg.csg3D().shapes3D().box3D(WIDTH+2, LENGTH+2, BOTTOM_HEIGHT+2)
		);
	}

	private Module3D topIntersect()
	{
		return csg.csg3D().construct3D().translate3D(0,0,0.5*(TOP_HEIGHT + 2)).add
		(
			csg.csg3D().shapes3D().box3D(WIDTH+2, LENGTH+2, TOP_HEIGHT+2)
		);
	}

	private Module3D bb()
	{
		return csg.csg3D().construct3D().rotateY(Angle.degrees(90)).add
		(
			csg.csg3D().shapes3D().cylinder3D
			(
					22 + 2*adjust.holeRoundTight().z(),
					7 + 2*adjust.holeSquareTight().xy(),
					128
			)
		);
	}

}
