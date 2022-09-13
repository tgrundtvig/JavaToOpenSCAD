package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;
import org.abstractica.javatoopenscad.corescad.impl.AGeometry;

public class Cylinder3D extends AGeometry implements Geometry3D
{
	private final double diameter;
	private final double height;
	private final int angularResolution;

	Cylinder3D(double diameter, double height, int angularResolution)
	{
		this.diameter = diameter;
		this.height = height;
		this.angularResolution = angularResolution;
	}

	public double getDiameter()
	{
		return diameter;
	}

	public double getHeight()
	{
		return height;
	}

	public int getAngularResolution()
	{
		return angularResolution;
	}
}