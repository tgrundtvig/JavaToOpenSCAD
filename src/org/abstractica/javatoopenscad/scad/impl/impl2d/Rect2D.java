package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.AGeometry;

public class Rect2D extends AGeometry implements Geometry2D
{
	private final double xSize;
	private final double ySize;

	public Rect2D(double xSize, double ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public double xSize()
	{
		return xSize;
	}

	public double ySize()
	{
		return ySize;
	}
}
