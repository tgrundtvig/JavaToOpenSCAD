package org.abstractica.javatoopenscad.corescad.corescad3d;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.Color;

public interface CoreSCAD3D
{
	//Module
	Geometry3D module3D(String name, Geometry3D geometry3D);

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


	//Objects
	Geometry3D sphere3D(double diameter, int angularResolution);
	Geometry3D cylinder3D(double diameter, double height, int angularResolution);
	Geometry3D cone3D(double bottomDiameter, double topDiameter, double height, int angularResolution);
	Geometry3D box3D(double sizeX, double sizeY, double sizeZ);
}
