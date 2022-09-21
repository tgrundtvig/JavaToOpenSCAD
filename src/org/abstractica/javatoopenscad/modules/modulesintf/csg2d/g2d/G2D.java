package org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d;


import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.polygons.Path2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;

public interface G2D
{
	//Polygons
	Path2D path2D(Iterable<Integer> path);
	Module2D polygon2D(Iterable<Vector2D> vertices);
	Module2D polygon2DMultiPaths(Iterable<Vector2D> points, Iterable<Path2D> paths);
	Module2D polygon2D(Iterable<Vector2D> points, Iterable<Integer> path);

	//Shapes

	//Square shapes
	Module2D unitSquare2D();
	Module2D rect2D(double width, double height);
	Module2D rectCorners2D(Vector2D cornerA, Vector2D cornerB);

	//Round shapes
	Module2D unitCircle2D(int angularResolution);
	Module2D circle2D(double diameter, int angularResolution);
	Module2D ellipse2D(double diameterX, double diameterY, int angularResolution);

	//Arrangements
	Module2D lineOfModule2D(Module2D module, int count, double distance);
}
