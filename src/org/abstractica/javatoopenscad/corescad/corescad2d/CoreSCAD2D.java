package org.abstractica.javatoopenscad.corescad.corescad2d;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.Color;

public interface CoreSCAD2D
{
	Node2D translate2D(Coord2D position);
	Node2D rotate2D(Angle x, Angle y, Angle z);
	Node2D rotate2D(Angle angle);
	Node2D scale2D(double xFactor, double yFactor);
	Node2D mirror2D(Coord2D normal);
	Node2D union2D();
	Node2D intersection2D();
	Node2D difference2D();
	Node2D hull2D();
	Node2D color2D(Color color);
	Geometry2D module2D(String name, Geometry2D geometry2D);
	Path2D path2D(Iterable<Integer> path);
	Geometry2D polygon2D(Iterable<Coord2D> vertices);
	Geometry2D polygon2DMultiPaths(Iterable<Coord2D> points, Iterable<Path2D> paths);
	Geometry2D polygon2D(Iterable<Coord2D> points, Iterable<Integer> path);

	//Objects
	Geometry2D circle2D(double diameter, int angularResolution);
	Geometry2D rect2D(double sizeX, double sizeY);
}
