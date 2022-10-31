package org.abstractica.openscadcsg.impl;

import org.abstractica.openscadcsg.intf.csg3d.Vector3D;

public class Vector3DImpl implements Vector3D
{
	private final double x;
	private final double y;
	private final double z;

	public Vector3DImpl(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
}
