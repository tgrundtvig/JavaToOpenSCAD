package org.abstractica.javatoopenscad.scad.impl.impl2d;
public class Offset2D extends ANode2D
{
	private final double delta;
	private final boolean chamfer;

	public Offset2D(double delta, boolean chamfer)
	{
		this.delta = delta;
		this.chamfer = chamfer;
	}

	public double getDelta()
	{
		return delta;
	}

	public boolean getChamfer()
	{
		return chamfer;
	}
}
