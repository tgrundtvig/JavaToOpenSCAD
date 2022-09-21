package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.impl.Coord2DImpl;
import org.abstractica.javatoopenscad.csg.Angle;

public interface Vector2D
{
	Vector2D ORIGO = new Coord2DImpl(0,0,0, Angle.ZERO);
	static Vector2D create(double x, double y)
	{
		if(x == 0 && y == 0) return ORIGO;
		double r = Math.sqrt(x*x + y*y);
		Angle theta = Angle.radians(Math.atan2(y, x));
		return new Coord2DImpl(x, y, r, theta);
	}

	double x();
	double y();
	Polar2D asPolar2D();
}
