package org.abstractica.javatoopenscad.scad.scad2d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.module.Arguments;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentsImplementation;

import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.module.impl.ArgumentsImpl;

public class Coord2D implements Arguments, ArgumentsImplementation
{
	public static final Coord2D ORIGO = new Coord2D(0,0,0, Angle.ZERO);

	public static Coord2D polar2D(double r, Angle theta)
	{
		if(r == 0)
		{
			return ORIGO;
		}
		double x = r * Math.cos(theta.asRadians());
		double y = r * Math.sin(theta.asRadians());
		return new Coord2D(x, y, r, theta);
	}

	public static Coord2D vector2D(double x, double y)
	{
		if(x == 0 && y == 0) return ORIGO;
		double r = Math.sqrt(x*x + y*y);
		Angle theta = Angle.rad(Math.atan2(y, x));
		return new Coord2D(x, y, r, theta);
	}


	private final ArgumentsImpl argImpl;
	private final double x, y, r;
	private final Angle theta;

	private Coord2D(double x, double y, double r, Angle theta)
	{
		argImpl = new ArgumentsImpl(this);
		this.x = x;
		this.y = y;
		this.r = r;
		this.theta = theta;
	}
	public double r()
	{
		return r;
	}
	public Angle theta()
	{
		return theta;
	}
	public double x()
	{
		return x;
	}
	public double y()
	{
		return y;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
	}

	@Override
	public String getClearText()
	{
		return argImpl.getClearText();
	}

	@Override
	public int getUniqueId()
	{
		return argImpl.getUniqueId();
	}
}
