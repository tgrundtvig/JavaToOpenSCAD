package org.abstractica.javatoopenscad.scadmodules;

import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule3D;


public interface SCADModules
{
	SCADModule2D unitSquare2D();

	SCADModule2D rectCenter2D(double width, double height);

	SCADModule2D rectCorners2D(Coord2D cornerA, Coord2D cornerB);

	SCADModule3D unitCube3D();
}
