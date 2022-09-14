package org.abstractica.javatoopenscad.modules;

import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.modules.impl.RectCenter2D;
import org.abstractica.javatoopenscad.modules.impl.RectCorners2D;

public interface Modules
{
	static RectCenter2D rectCenter2D(double width, double height)
	{
		return new RectCenter2D(width, height);
	}

	static RectCorners2D rectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		return new RectCorners2D(cornerA, cornerB);
	}
}
