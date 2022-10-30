package org.abstractica.javatoopenscad.csg.math;

import org.abstractica.javatoopenscad.csg.math.impl.AngleImpl;

public interface Angle
{
	Angle ZERO = new AngleImpl(0);
	static Angle rotations(double rotations)
	{
		if(rotations == 0.0)
		{
			return ZERO;
		}
		return new AngleImpl(rotations);
	}
	static Angle radians(double radians)
	{
		return rotations(radians / (Math.PI*2));
	}
	static Angle degrees(double degrees)
	{
		return rotations(degrees / 360.0);
	}

	static Angle add(Angle a, Angle b)
	{
		return rotations(a.asRotations() + b.asRotations());
	}
	static Angle subtract(Angle a, Angle b)
	{
		return rotations(a.asRotations() - b.asRotations());
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
		return radians(Math.atan(a));
	}

	static Angle atan2(double y, double x)
	{
		return radians(Math.atan2(y,x));
	}

	double asRadians();
	double asDegrees();
	double asRotations();
}
