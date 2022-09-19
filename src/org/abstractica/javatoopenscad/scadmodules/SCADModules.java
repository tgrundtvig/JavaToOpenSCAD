package org.abstractica.javatoopenscad.scadmodules;

import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule3D;
import org.abstractica.javatoopenscad.scad.scad3d.Coord3D;


public interface SCADModules
{
	SCADModule2D unitCircle2D(int angularResolution);

	SCADModule2D circle2D(double diameter, int angularResolution);

	SCADModule2D ellipse2D(double diameterX, double diameterY, int angularResolution);

	SCADModule2D unitSquare2D();

	SCADModule2D rect2D(double width, double height);

	SCADModule2D rectCorners2D(Coord2D cornerA, Coord2D cornerB);

	SCADModule2D lineOf(SCADModule2D module, int count, double distance);

	SCADModule3D unitCube3D();

	SCADModule3D box3D(double xSize, double ySize, double zSize);

	SCADModule3D boxCorners3D(Coord3D cornerA, Coord3D cornerB);
}
