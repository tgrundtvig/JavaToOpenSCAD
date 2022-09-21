package org.abstractica.javatoopenscad.coreimpl.core.impl;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Polar3D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector3D;

public class Coord3DImpl implements Vector3D, Polar3D, HasArguments
{
	private final double x;
	private final double y;
	private final double z;
	private final double r;
	private final Angle theta;
	private final Angle elevation;

	public Coord3DImpl(double x, double y, double z, double r, Angle theta, Angle elevation)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.theta = theta;
		this.elevation = elevation;
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
	public Angle elevation()
	{
		return elevation;
	}

	@Override
	public Vector3D asVector3D()
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
	public double z()
	{
		return z;
	}

	@Override
	public Polar3D asPolar3D()
	{
		return this;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
	}

	@Override
	public String getSimpleName()
	{
		return "Vector3D";
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Coord3DImpl)) return false;

		Coord3DImpl coord3D = (Coord3DImpl) o;

		if (Double.compare(coord3D.x, x) != 0) return false;
		if (Double.compare(coord3D.y, y) != 0) return false;
		return Double.compare(coord3D.z, z) == 0;
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
		temp = Double.doubleToLongBits(z);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
