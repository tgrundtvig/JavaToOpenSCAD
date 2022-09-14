package org.abstractica.javatoopenscad.scad.scad2d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.impl.impl2d.Coord2DImpl;

public interface Coord2D
{
	static final Coord2D ORIGO = new Coord2DImpl(0,0,0, Angle.ZERO);

	static Polar2D polar2D(double r, Angle theta)
	{
		if(r == 0)
		{
			return ORIGO.asPolar2D();
		}
		double x = r * Math.cos(theta.asRadians());
		double y = r * Math.sin(theta.asRadians());
		return new Coord2DImpl(x, y, r, theta);
	}

	static Vector2D vector(double x, double y)
	{
		if(x == 0 && y == 0) return ORIGO.asVector2D();
		double r = Math.sqrt(x*x + y*y);
		Angle theta = Angle.rad(Math.atan2(y, x));
		return new Coord2DImpl(x, y, r, theta);
	}

	Vector2D asVector2D();
	Polar2D asPolar2D();
}
