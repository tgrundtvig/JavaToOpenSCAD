package refactoring.modules.modulesintf.csg2d.g2d;


import refactoring.coreimpl.core.Module2D;
import refactoring.modules.modulesintf.csg2d.g2d.polygons.Path2D;
import refactoring.modules.modulesintf.math.Vector2D;

public interface G2D
{
	//Polygons
	Path2D path2D(Iterable<Integer> path);
	Module2D polygon2D(Iterable<Vector2D> vertices);
	Module2D polygon2DMultiPaths(Iterable<Vector2D> points, Iterable<Path2D> paths);
	Module2D polygon2D(Iterable<Vector2D> points, Iterable<Integer> path);

	//Shapes
	Module2D UnitSquare2D();
}
