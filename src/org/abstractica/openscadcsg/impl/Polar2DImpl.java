package org.abstractica.openscadcsg.impl;

import org.abstractica.openscadcsg.intf.Angle;
import org.abstractica.openscadcsg.intf.csg2D.Polar2D;

public class Polar2DImpl implements Polar2D
{
	private final double r;
	private final Angle theta;

	public Polar2DImpl(double r, Angle theta)
	{
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
}
