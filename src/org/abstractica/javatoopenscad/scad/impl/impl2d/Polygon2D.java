package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.Path2D;
import org.abstractica.javatoopenscad.scad.impl.AGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon2D extends AGeometry implements Geometry2D
{
	private final List<Coord2D> vertices;
	private final List<Path2D> paths;
	public Polygon2D(Iterable<Coord2D> vertices, Iterable<Path2D> paths)
	{
		ArrayList<Coord2D> newVertices = new ArrayList<>();
		ArrayList<Path2D> newPaths = new ArrayList<>();
		for(Coord2D v : vertices)
		{
			newVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(newVertices);
		for (Path2D path : paths)
		{
			newPaths.add(path);
		}
		this.paths = Collections.unmodifiableList(newPaths);
	}

	public Polygon2D(Iterable<Coord2D> vertices)
	{
		ArrayList<Coord2D> newPoints = new ArrayList<>();
		for(Coord2D v : vertices)
		{
			newPoints.add(v);
		}
		this.vertices = Collections.unmodifiableList(newPoints);
		this.paths = null;
	}

	public List<Coord2D> getVertices()
	{
		return vertices;
	}

	public List<Path2D> getPaths()
	{
		return paths;
	}
}
