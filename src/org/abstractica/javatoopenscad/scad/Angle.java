package org.abstractica.javatoopenscad.scad;

import org.abstractica.javatoopenscad.scad.impl.AngleImpl;

public interface Angle
{
	static final Angle ZERO = rot(0);
	static Angle rad(double radians)
	{
		return rot(radians / (Math.PI*2));
	}
	static Angle deg(double degrees)
	{
		return rot(degrees / 360.0);
	}

	static Angle rot(double rotations)
	{
		return new AngleImpl(rotations);
	}

	static Angle add(Angle a, Angle b)
	{
		return rot(a.asRotations() + b.asRotations());
	}

	static Angle subtract(Angle a, Angle b)
	{
		return rot(a.asRotations() - b.asRotations());
	}

	static Angle fromTo(Angle from, Angle to)
	{
		return subtract(to, from);
	}

	static double sin(Angle angle)
	{
		return Math.sin(angle.asRadians());
	}

	static double cos(Angle angle)
	{
		return Math.cos(angle.asRadians());
	}

	static double tan(Angle angle)
	{
		return Math.tan(angle.asRadians());
	}

	static Angle atan(double a)
	{
		return rad(Math.atan(a));
	}

	static Angle atan2(double y, double x)
	{
		return rad(Math.atan2(y,x));
	}

	double asRadians();
	double asDegrees();
	double asRotations();
}
