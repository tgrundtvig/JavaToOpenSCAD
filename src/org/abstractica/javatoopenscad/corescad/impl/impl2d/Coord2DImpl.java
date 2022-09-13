package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.corescad2d.Coord2D;
import org.abstractica.javatoopenscad.corescad.corescad2d.Polar2D;
import org.abstractica.javatoopenscad.corescad.corescad2d.Vector2D;

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
}
