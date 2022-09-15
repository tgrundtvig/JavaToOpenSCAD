package org.abstractica.javatoopenscad.scad;

import org.abstractica.javatoopenscad.scad.module.AArguments;
import org.abstractica.javatoopenscad.scad.module.ArgumentCollector;
import org.abstractica.javatoopenscad.util.Str;

public class Angle extends AArguments
{
	//Static helpers
	public static final Angle ZERO = rot(0);
	public static Angle rad(double radians)
	{
		return rot(radians / (Math.PI*2));
	}
	public static Angle deg(double degrees)
	{
		return rot(degrees / 360.0);
	}
	public static Angle rot(double rotations)
	{
		return new Angle(rotations);
	}
	public static Angle add(Angle a, Angle b)
	{
		return rot(a.asRotations() + b.asRotations());
	}
	public static Angle subtract(Angle a, Angle b)
	{
		return rot(a.asRotations() - b.asRotations());
	}

	public static Angle fromTo(Angle from, Angle to)
	{
		return subtract(to, from);
	}

	public static double sin(Angle angle)
	{
		return Math.sin(angle.asRadians());
	}

	public static double cos(Angle angle)
	{
		return Math.cos(angle.asRadians());
	}

	public static double tan(Angle angle)
	{
		return Math.tan(angle.asRadians());
	}

	public static Angle atan(double a)
	{
		return rad(Math.atan(a));
	}

	public static Angle atan2(double y, double x)
	{
		return rad(Math.atan2(y,x));
	}


	//Non-static
	private final double rotations;

	private Angle(double rotations)
	{
		while(rotations <= -0.5)
		{
			++rotations;
		}
		while(rotations > 0.5)
		{
			--rotations;
		}
		this.rotations = rotations;
	}

	public double asRadians()
	{
		return rotations*(Math.PI*2);
	}

	public double asDegrees()
	{
		return rotations*360.0;
	}

	public double asRotations()
	{
		return rotations;
	}

	public String toString()
	{
		String deg = Str.f(asDegrees(),0);
		String rotp = Str.f(asRotations()*100,0);
		return "Angle(deg: " + deg +
				", %rot: " + rotp + ")";
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("deg", Double.toString(asDegrees()));
	}
}
