package org.abstractica.javatoopenscad.scad.scad3d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Color;

public interface SCAD3D
{
	//3D operations
	Node3D translate3D(Coord3D vector);
	Node3D rotate3D(Angle x, Angle y, Angle z);
	Node3D scale3D(double xFactor, double yFactor, double zFactor);
	Node3D mirror3D(Coord3D normal);
	Node3D union3D();
	Node3D intersection3D();
	Node3D difference3D();
	Node3D hull3D();
	Node3D color3D(Color color);
}
