package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.math.Vector2D;

public interface Shapes2D
{
	//Square shapes
	Module2D unitSquare2D();
	Module2D rect2D(double width, double height);
	Module2D rectCorners2D(Vector2D cornerA, Vector2D cornerB);

	//Round shapes
	Module2D unitCircle2D(int angularResolution);
	Module2D circle2D(double diameter, int angularResolution);
	Module2D hollowCircle2D(double innerDiameter, double outerDiameter, int angularResolution);
	Module2D ellipse2D(double diameterX, double diameterY, int angularResolution);

	//Triangles
	Module2D isoscelesTriangle(double base, double height);

	//Pie
	Module2D pie2D(double diameter, Angle start, Angle end, int steps);
}
