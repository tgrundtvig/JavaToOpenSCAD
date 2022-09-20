package refactoring.modules.modulesimpl.csg2d.g2dfrom2d;


import refactoring.coreimpl.core.Module2DFrom2D;
import refactoring.coreimpl.core.ModuleFactory;
import refactoring.modules.modulesimpl.builtin.Rotate;
import refactoring.modules.modulesimpl.builtin.Color;
import refactoring.modules.modulesimpl.builtin.Translate2D;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import refactoring.coreimpl.core.impl.AModuleFactory;
import refactoring.modules.modulesintf.math.Angle;

public class G2DFrom2DImpl extends AModuleFactory implements G2DFrom2D
{
	public G2DFrom2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module2DFrom2D translate2D(double x, double y)
	{
		return module2DFrom2D(new Translate2D(x, y));
	}

	@Override
	public Module2DFrom2D rotate2D(Angle angle)
	{
		return module2DFrom2D(new Rotate(CSGMath.ANGLE_ZERO, CSGMath.ANGLE_ZERO, angle));
	}

	@Override
	public Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z)
	{
		return module2DFrom2D(new Rotate(x, y, z));
	}

	@Override
	public Module2DFrom2D color2D(Color color)
	{
		return module2DFrom2D(color);
	}
}
