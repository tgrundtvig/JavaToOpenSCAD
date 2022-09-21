package org.abstractica.javatoopenscad.modules.modulesimpl.csg3d.g3dfrom2d;

import org.abstractica.javatoopenscad.coreimpl.core.Module3DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.LinearExtrude;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.RotateExtrude;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;

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
	public Module3DFrom2D linearExtrude(double height, int convexity)
	{
		return linearExtrude(height, CSGMath.ANGLE_ZERO, 1, 1, convexity);
	}

	@Override
	public Module3DFrom2D linearExtrude(double height, double scale, int convexity)
	{
		return linearExtrude(height, CSGMath.ANGLE_ZERO, scale, 1, convexity);
	}

	@Override
	public Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity)
	{
		return module3DFrom2D(new RotateExtrude(angle, angularResolution, convexity));
	}
}
