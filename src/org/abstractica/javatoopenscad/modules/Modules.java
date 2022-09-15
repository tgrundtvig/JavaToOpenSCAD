package org.abstractica.javatoopenscad.modules;

import org.abstractica.javatoopenscad.modules.impl.RectCenter2D;
import org.abstractica.javatoopenscad.modules.impl.RectCorners2D;
import org.abstractica.javatoopenscad.modules.impl.UnitSquare2D;
import org.abstractica.javatoopenscad.scad.Coord2D;


public interface Modules
{
	UnitSquare2D unitSquare2D();

	RectCenter2D rectCenter2D(double width, double height);

	RectCorners2D rectCorners2D(Coord2D cornerA, Coord2D cornerB);
}
