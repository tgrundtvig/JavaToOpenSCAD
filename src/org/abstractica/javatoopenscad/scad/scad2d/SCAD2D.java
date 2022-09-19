package org.abstractica.javatoopenscad.scad.scad2d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.impl.Color;

public interface SCAD2D
{
	Node2D translate2D(Coord2D position);
	Node2D rotate2D(Angle x, Angle y, Angle z);
	Node2D rotate2D(Angle angle);
	Node2D scale2D(double xFactor, double yFactor);
	Node2D offset2D(double delta, boolean chamfer);
	Node2D offsetRound2D(double radius, int angularResolution);
	Node2D mirror2D(Coord2D normal);
	Node2D union2D();
	Node2D intersection2D();
	Node2D difference2D();
	Node2D hull2D();
	Node2D color2D(Color color);
	Path2D path2D(Iterable<Integer> path);
	Geometry2D polygon2D(Iterable<Coord2D> vertices);
	Geometry2D polygon2DMultiPaths(Iterable<Coord2D> points, Iterable<Path2D> paths);
	Geometry2D polygon2D(Iterable<Coord2D> points, Iterable<Integer> path);
}
