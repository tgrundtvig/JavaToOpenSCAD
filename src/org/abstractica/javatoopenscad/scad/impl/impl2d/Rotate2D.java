package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.Angle;

public class Rotate2D extends ANode2D
{
	private final Angle x;
	private final Angle y;
	private final Angle z;

	Rotate2D(Angle x, Angle y, Angle z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Angle x()
	{
		return x;
	}

	public Angle y()
	{
		return y;
	}

	public Angle z()
	{
		return z;
	}
}
