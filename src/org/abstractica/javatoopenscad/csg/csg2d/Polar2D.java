package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.csg.Angle;

public interface Polar2D
{
	Polar2D ORIGO = Vector2D.ORIGO.asPolar2D();
	static Polar2D create(double r, Angle theta)
	{
		if(r == 0)
		{
			return ORIGO;
		}
		double x = r * Math.cos(theta.asRadians());
		double y = r * Math.sin(theta.asRadians());
		return Vector2D.create(x, y).asPolar2D(); //Make sure Theta is in ]-0.5 : 0.5]
	}
	double r();
	Angle theta();
	Vector2D asVector2D();
}
