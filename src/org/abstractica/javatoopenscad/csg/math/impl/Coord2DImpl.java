package org.abstractica.javatoopenscad.csg.math.impl;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.math.Polar2D;
import org.abstractica.javatoopenscad.csg.math.Vector2D;

public class Coord2DImpl implements Vector2D, Polar2D, HasArguments
{
	private final double x;
	private final double y;
	private final double r;
	private final Angle theta;

	public Coord2DImpl(double x, double y, double r, Angle theta)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.theta = theta;
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
	public Vector2D asVector2D()
	{
		return this;
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
	public Polar2D asPolar2D()
	{
		return this;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
	}

	@Override
	public String getSimpleName()
	{
		return "Vector2D";
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Coord2DImpl)) return false;

		Coord2DImpl coord2D = (Coord2DImpl) o;

		if (Double.compare(coord2D.x, x) != 0) return false;
		return Double.compare(coord2D.y, y) == 0;
	}

	@Override
	public int hashCode()
	{
		int result;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
