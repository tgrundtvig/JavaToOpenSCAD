package org.abstractica.javatoopenscad.scad.impl.impl2d;
public class OffsetRound2D extends ANode2D
{
	private final double radius;
	private final int angularResolution;

	public OffsetRound2D(double radius, int angularResolution)
	{
		this.radius = radius;
		this.angularResolution = angularResolution;
	}

	public double getRadius()
	{
		return radius;
	}

	public int getAngularResolution()
	{
		return angularResolution;
	}
}
