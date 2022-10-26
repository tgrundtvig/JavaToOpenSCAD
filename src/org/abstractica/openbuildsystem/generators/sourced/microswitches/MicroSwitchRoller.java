package org.abstractica.openbuildsystem.generators.sourced.microswitches;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.openbuildsystem.Print3DAdjust;

public class MicroSwitchRoller
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private static final double LENGTH = 20.1;
	private static final double WIDTH = 6.4;
	private static final double PIN_DIAMETER = 2.4;
	private static final double PIN_HEIGHT = 2.6;

	public MicroSwitchRoller(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}

	public Module3D microSwitchRollerCutout(double topClearance, double bottomClearance)
	{
		Shapes3D s3d = csg.csg3D().shapes3D();
		Construct3D c3d = csg.csg3D().construct3D();
		Module3DFrom3D diff = c3d.difference3D();
		Module3DFrom3D union = c3d.union3D();
		Module3DFrom3D t = c3d.translate3D(0,-0.5*(topClearance+bottomClearance) + topClearance,0);
		Module3DFrom3D r = c3d.rotateX(Angle.degrees(-90));
		Module3D box = s3d.box3D(LENGTH+2*adjust.holeSquareTight().xy(),
				topClearance + bottomClearance,
				WIDTH + 2*adjust.holeSquareTight().xy());
		Module3D cyl = s3d.cylinder3D(WIDTH + 2*adjust.holeSquareTight().xy(), topClearance+10, 64);
		diff.add(union);
		union.add(t).add(r);
		t.add(box);
		r.add(cyl);
		diff.add(pins());
		return diff;
	}

	private Module3D pins()
	{
		Module3D pin = csg.csg3D().shapes3D().cylinder3D
		(
			PIN_DIAMETER + 2*adjust.solidRoundTight().xy(),
			PIN_HEIGHT + 2*adjust.solidSquareTight().z(),
			16
		);
		Construct3D c3d = csg.csg3D().construct3D();
		double depth = 0.5*PIN_HEIGHT-0.5*WIDTH-1;
		Module3DFrom3D union = c3d.union3D();
		union.add(c3d.translate3D(-5,0,depth).add(pin));
		union.add(c3d.translate3D(5,0,depth).add(pin));
		union.add(c3d.translate3D(-5,0,-depth).add(pin));
		union.add(c3d.translate3D(5,0,-depth).add(pin));
		return union;
	}
}
