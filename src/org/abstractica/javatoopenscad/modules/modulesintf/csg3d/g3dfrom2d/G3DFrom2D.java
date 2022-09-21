package org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom2d;


import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.coreimpl.core.Module3DFrom2D;

public interface G3DFrom2D
{
	// Linear extrude
	Module3DFrom2D linearExtrude(double height, Angle twist, double scale, int slices, int convexity);
	Module3DFrom2D linearExtrude(double height, int convexity);
	Module3DFrom2D linearExtrude(double height, double scale, int convexity);

	// Rotate extrude
	Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity);
}
