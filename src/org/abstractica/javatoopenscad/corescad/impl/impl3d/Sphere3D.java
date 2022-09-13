package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;
import org.abstractica.javatoopenscad.corescad.impl.AGeometry;

public class Sphere3D extends AGeometry implements Geometry3D
{
	private final double diameter;
	private final int angularResolution;

	Sphere3D(double diameter, int angularResolution)
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
