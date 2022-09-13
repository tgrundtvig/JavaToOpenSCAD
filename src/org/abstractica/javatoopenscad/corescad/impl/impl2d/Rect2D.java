package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.corescad2d.Geometry2D;
import org.abstractica.javatoopenscad.corescad.impl.AGeometry;

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