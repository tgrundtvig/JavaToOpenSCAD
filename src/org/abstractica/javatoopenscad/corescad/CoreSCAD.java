package org.abstractica.javatoopenscad.corescad;

import org.abstractica.javatoopenscad.corescad.corescad2d.Geometry2D;
import org.abstractica.javatoopenscad.corescad.corescad2d.CoreSCAD2D;
import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;
import org.abstractica.javatoopenscad.corescad.corescad3d.CoreSCAD3D;

public interface CoreSCAD
{
	CoreSCAD2D getSCAD2D();
	CoreSCAD3D getSCAD3D();

	//3D to 2D operations
	Geometry2D project(Geometry3D geometry3D, boolean cut);

	//2D to 3D operations
	Geometry3D linearExtrude(Geometry2D geometry2D,
	                         double height,
	                         Angle twist,
	                         double scale,
	                         int slices,
	                         int convexity);
	Geometry3D rotateExtrude(Geometry2D geometry2D, Angle angle, int angularResolution, int convexity);
}
