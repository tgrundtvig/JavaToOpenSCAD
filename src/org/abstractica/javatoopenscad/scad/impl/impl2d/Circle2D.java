package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.AGeometry;

public class Circle2D extends AGeometry implements Geometry2D
{
	private final double diameter;
	private final int angularResolution;

	public Circle2D(double diameter, int angularResolution)
	{
		this.diameter = diameter;
		this.angularResolution = angularResolution;
	}

	public double getDiameter()
	{
		return diameter;
	}

	public int getAngularResolution()
	{
		return angularResolution;
	}
}
