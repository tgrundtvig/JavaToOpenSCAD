package refactoring.modules.modulesimpl.csg2d.g2d;

import refactoring.coreimpl.core.Module2D;
import refactoring.coreimpl.core.ModuleFactory;
import refactoring.modules.modulesimpl.csg2d.g2d.modules.UnitSquare2D;
import refactoring.modules.modulesimpl.builtin.polygon2d.Path2DImpl;
import refactoring.modules.modulesimpl.builtin.polygon2d.Polygon2D;
import refactoring.modules.modulesintf.csg2d.g2d.G2D;
import refactoring.coreimpl.core.impl.AModuleFactory;
import refactoring.modules.modulesintf.csg2d.g2d.polygons.Path2D;
import refactoring.modules.modulesintf.math.Vector2D;

import java.util.ArrayList;


public class G2DImpl extends AModuleFactory implements G2D
{
	public G2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Path2D path2D(Iterable<Integer> path)
	{
		return new Path2DImpl(path);
	}

	@Override
	public Module2D polygon2D(Iterable<Vector2D> vertices)
	{
		return module2D(new Polygon2D(vertices));
	}

	@Override
	public Module2D polygon2DMultiPaths(Iterable<Vector2D> points, Iterable<Path2D> paths)
	{
		return module2D(new Polygon2D(points, paths));
	}

	@Override
	public Module2D polygon2D(Iterable<Vector2D> points, Iterable<Integer> path)
	{
		ArrayList<Path2D> paths = new ArrayList<>();
		paths.add(path2D(path));
		return polygon2DMultiPaths(points, paths);
	}

	@Override
	public Module2D UnitSquare2D()
	{
		return module2D(new UnitSquare2D());
	}

}
