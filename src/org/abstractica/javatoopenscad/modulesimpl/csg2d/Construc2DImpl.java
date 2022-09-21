package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Path2D;
import org.abstractica.javatoopenscad.csg.csg2d.Polar2D;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.modulesimpl.common.*;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.*;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.polygon2d.Path2DImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.polygon2d.Polygon2D;

import java.util.ArrayList;

public class Construc2DImpl extends AModuleFactory implements Construct2D
{
	public Construc2DImpl(ModuleFactory factory)
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
	public Module2DFrom2D translate2D(double x, double y)
	{
		return module2DFrom2D(new Translate2D(x, y));
	}

	@Override
	public Module2DFrom2D translate2D(Vector2D t)
	{
		return translate2D(t.x(), t.y());
	}

	@Override
	public Module2DFrom2D translate2D(Polar2D p)
	{
		return translate2D(p.asVector2D());
	}

	@Override
	public Module2DFrom2D rotate2D(Angle angle)
	{
		return module2DFrom2D(new Rotate(Angle.ZERO, Angle.ZERO, angle));
	}

	@Override
	public Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z)
	{
		return module2DFrom2D(new Rotate(x, y, z));
	}

	@Override
	public Module2DFrom2D scale2D(double x, double y)
	{
		return module2DFrom2D(new Scale2D(x, y));
	}

	@Override
	public Module2DFrom2D scale2D(Vector2D s)
	{
		return scale2D(s.x(), s.y());
	}

	@Override
	public Module2DFrom2D mirror2D(double normX, double normY)
	{
		return module2DFrom2D(new Mirror2D(normX, normY));
	}

	@Override
	public Module2DFrom2D mirror2D(Vector2D norm)
	{
		return mirror2D(norm.x(), norm.y());
	}

	@Override
	public Module2DFrom2D mirror2D(Polar2D norm)
	{
		return mirror2D(norm.asVector2D());
	}

	@Override
	public Module2DFrom2D union2D()
	{
		return module2DFrom2D(Union.instance);
	}

	@Override
	public Module2DFrom2D intersection2D()
	{
		return module2DFrom2D(Intersection.instance);
	}

	@Override
	public Module2DFrom2D difference2D()
	{
		return module2DFrom2D(Difference.instance);
	}

	@Override
	public Module2DFrom2D hull2D()
	{
		return module2DFrom2D(Hull.instance);
	}

	@Override
	public Module2DFrom2D offset2D(double delta, boolean chamfer)
	{
		return module2DFrom2D(new Offset2D(delta, chamfer));
	}

	@Override
	public Module2DFrom2D offsetRound2D(double radius, int angularResolution)
	{
		return module2DFrom2D(new OffsetRound2D(radius, angularResolution));
	}

}
