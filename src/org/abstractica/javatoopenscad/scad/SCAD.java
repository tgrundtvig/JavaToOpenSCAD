package org.abstractica.javatoopenscad.scad;

import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.SCAD2D;
import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scad.scad3d.SCAD3D;

public interface SCAD
{
	SCAD2D getSCAD2D();
	SCAD3D getSCAD3D();

	//3D to 2D operations
	Geometry2D project(Geometry3D geometry3D, boolean cut);

	//2D to 3D operations
	Node2DToGeometry3D linearExtrude(Geometry2D geometry2D,
	                         double height,
	                         Angle twist,
	                         double scale,
	                         int slices,
	                         int convexity);
	Node2DToGeometry3D rotateExtrude(Geometry2D geometry2D, Angle angle, int angularResolution, int convexity);
}
