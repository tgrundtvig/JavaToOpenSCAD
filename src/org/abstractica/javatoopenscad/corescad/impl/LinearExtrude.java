package org.abstractica.javatoopenscad.corescad.impl;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.corescad2d.Geometry2D;
import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;

public class LinearExtrude extends AGeometry implements Geometry3D
{
	private final Geometry2D geometry2D;
	private final double height;
	private final Angle twist;
	private final double scale;
	private final int slices;
	private final int convexity;

	public LinearExtrude(Geometry2D geometry2D, double height, Angle twist, double scale, int slices, int convexity)
	{
		this.geometry2D = geometry2D;
		this.height = height;
		this.twist = twist;
		this.scale = scale;
		this.slices = slices;
		this.convexity = convexity;
	}

	public Geometry2D getGeometry2D()
	{
		return geometry2D;
	}

	public double getHeight()
	{
		return height;
	}

	public Angle getTwist()
	{
		return twist;
	}

	public double getScale()
	{
		return scale;
	}

	public int getSlices()
	{
		return slices;
	}

	public int getConvexity()
	{
		return convexity;
	}
}
