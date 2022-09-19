package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.impl.Color;
import org.abstractica.javatoopenscad.scad.scad2d.*;

import java.util.ArrayList;

public class SCAD2DImpl implements SCAD2D
{
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
	public Node2D offset2D(double delta, boolean chamfer)
	{
		return new Offset2D(delta, chamfer);
	}

	@Override
	public Node2D offsetRound2D(double radius, int angularResolution)
	{
		return new OffsetRound2D(radius, angularResolution);
	}

	@Override
	public Node2D mirror2D(Coord2D normal)
	{
		return new Mirror2D(normal.x(), normal.y());
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
