package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.Angle;

public class Rotate3D extends ANode3D
{
	private final Angle x;
	private final Angle y;
	private final Angle z;

	public Rotate3D(Angle x, Angle y, Angle z)
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
