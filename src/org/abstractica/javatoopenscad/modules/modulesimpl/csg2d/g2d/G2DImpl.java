package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d;

import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.modules.*;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.polygon2d.Path2DImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.polygon2d.Polygon2D;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.polygons.Path2D;

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
	public Module2D unitSquare2D()
	{
		return module2D(UnitSquare2D.instance);
	}

	@Override
	public Module2D rect2D(double width, double height)
	{
		return module2D(new Rect2D(width, height));
	}

	@Override
	public Module2D rectCorners2D(Vector2D cornerA, Vector2D cornerB)
	{
		return module2D(new RectCorners2D(cornerA, cornerB));
	}

	@Override
	public Module2D unitCircle2D(int angularResolution)
	{
		return module2D(new UnitCircle2D(angularResolution));
	}

	@Override
	public Module2D circle2D(double diameter, int angularResolution)
	{
		return module2D(new Circle2D(diameter, angularResolution));
	}

	@Override
	public Module2D ellipse2D(double diameterX, double diameterY, int angularResolution)
	{
		return module2D(new Ellipse2D(diameterX, diameterY, angularResolution));
	}

	@Override
	public Module2D lineOfModule2D(Module2D module2D, int count, double distance)
	{
		return module2D(new LineOf2D(module2D, count, distance));
	}

}
