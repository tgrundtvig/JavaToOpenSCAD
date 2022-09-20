package refactoring.modules.modulesintf.csg3d.g3dfrom2d;


import refactoring.coreimpl.core.Module3DFrom2D;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.math.Angle;

public interface G3DFrom2D
{
	// Linear extrude
	Module3DFrom2D linearExtrude(double height, Angle twist, double scale, int slices, int convexity);
	default Module3DFrom2D linearExtrude(double height, int convexity)
	{
		return linearExtrude(height, CSGMath.ANGLE_ZERO, 1, 1, convexity);
	}
	default Module3DFrom2D linearExtrude(double height, double scale, int convexity)
	{
		return linearExtrude(height, CSGMath.ANGLE_ZERO, scale, 1, convexity);
	}

	// Rotate extrude
	Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity);
}
