package refactoring.modules.modulesimpl.csg3d.g3dfrom2d;

import refactoring.coreimpl.core.Module3DFrom2D;
import refactoring.coreimpl.core.ModuleFactory;
import refactoring.coreimpl.core.impl.AModuleFactory;
import refactoring.modules.modulesimpl.builtin.LinearExtrude;
import refactoring.modules.modulesimpl.builtin.RotateExtrude;
import refactoring.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;
import refactoring.modules.modulesintf.math.Angle;

public class G3DFrom2DImpl extends AModuleFactory implements G3DFrom2D
{
	public G3DFrom2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module3DFrom2D linearExtrude(double height, Angle twist, double scale, int slices, int convexity)
	{
		return module3DFrom2D(new LinearExtrude(height, twist, scale, slices, convexity));
	}

	@Override
	public Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity)
	{
		return module3DFrom2D(new RotateExtrude(angle, angularResolution, convexity));
	}
}
