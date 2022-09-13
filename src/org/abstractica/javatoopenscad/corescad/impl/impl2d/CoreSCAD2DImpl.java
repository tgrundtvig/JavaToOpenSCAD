package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.Color;
import org.abstractica.javatoopenscad.corescad.corescad2d.*;

import java.util.ArrayList;

public class CoreSCAD2DImpl implements CoreSCAD2D
{
	@Override
	public Geometry2D module2D(String name, Geometry2D geometry2D)
	{
		return new Module2D(name, geometry2D);
	}

	@Override
	public Path2D path2D(Iterable<Integer> path)
	{
		return new Path2DImpl(path);
	}

	@Override
	public Geometry2D polygon2D(Iterable<Coord2D> vertices)
	{
		return new Polygon2D(vertices);
	}

	@Override
	public Geometry2D polygon2DMultiPaths(Iterable<Coord2D> points, Iterable<Path2D> paths)
	{
		return new Polygon2D(points, paths);
	}

	@Override
	public Geometry2D polygon2D(Iterable<Coord2D> points, Iterable<Integer> path)
	{
		ArrayList<Path2D> paths = new ArrayList<>();
		paths.add(path2D(path));
		return polygon2DMultiPaths(points, paths);
	}

	@Override
	public Geometry2D circle2D(double diameter, int angularResolution)
	{
		return new Circle2D(diameter, angularResolution);
	}

	@Override
	public Geometry2D rect2D(double xSize, double ySize)
	{
		return new Rect2D(xSize,ySize);
	}

	@Override
	public Node2D translate2D(Coord2D vector)
	{
		return new Translate2D(vector);
	}

	@Override
	public Node2D rotate2D(Angle x, Angle y, Angle z)
	{
		return new Rotate2D(x, y, z);
	}

	@Override
	public Node2D rotate2D(Angle angle)
	{
		return new Rotate2D(Angle.ZERO, Angle.ZERO, angle);
	}

	@Override
	public Node2D scale2D(double xFactor, double yFactor)
	{
		return new Scale2D(xFactor, yFactor);
	}

	@Override
	public Node2D mirror2D(Coord2D normal)
	{
		Vector2D norm = normal.asVector2D();
		return new Mirror2D(norm.x(), norm.y());
	}

	@Override
	public Node2D union2D()
	{
		return new Union2D();
	}

	@Override
	public Node2D intersection2D()
	{
		return new Intersection2D();
	}

	@Override
	public Node2D difference2D()
	{
		return new Difference2D();
	}

	@Override
	public Node2D hull2D()
	{
		return new Hull2D();
	}

	@Override
	public Node2D color2D(Color color)
	{
		return new Color2D(color);
	}
}
