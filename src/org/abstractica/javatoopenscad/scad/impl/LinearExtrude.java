package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Node2DToGeometry3D;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.impl2d.ANode2D;

public class LinearExtrude extends ANode2D implements Node2DToGeometry3D
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
