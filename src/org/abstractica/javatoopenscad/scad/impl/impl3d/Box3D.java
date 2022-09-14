package org.abstractica.javatoopenscad.scad.impl.impl3d;

import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scad.impl.AGeometry;

public class Box3D extends AGeometry implements Geometry3D
{
	private final double xSize;
	private final double ySize;
	private final double zSize;

	public Box3D(double xSize, double ySize, double zSize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
	}

	public double xSize()
	{
		return xSize;
	}

	public double ySize()
	{
		return ySize;
	}

	public double zSize()
	{
		return zSize;
	}
}
