package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Polar2D;
import org.abstractica.javatoopenscad.scad.scad2d.Vector2D;

public class Coord2DImpl implements Coord2D, Vector2D, Polar2D
{
	private final double x, y, r;
	private final Angle theta;

	public Coord2DImpl(double x, double y, double r, Angle theta)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.theta = theta;
	}

	@Override
	public Vector2D asVector2D()
	{
		return this;
	}

	@Override
	public Polar2D asPolar2D()
	{
		return this;
	}


	@Override
	public double r()
	{
		return r;
	}

	@Override
	public Angle theta()
	{
		return theta;
	}

	@Override
	public double x()
	{
		return x;
	}

	@Override
	public double y()
	{
		return y;
	}

	@Override
	public String toVector2DString()
	{
		return "V2("+ d(x) + ", " + d(y) + ")";
	}

	private String d(double d)
	{
		d = d * 100;
		d = Math.round(d);
		d = d / 100;
		return Double.toString(d);
	}
}
