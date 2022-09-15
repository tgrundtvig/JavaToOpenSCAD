package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Node2DToGeometry3D;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.impl2d.ANode2D;

public class RotateExtrude extends ANode2D implements Node2DToGeometry3D
{
	private final Geometry2D geometry2D;
	private final Angle angle;
	private final int angularResolution;
	private final int convexity;

	public RotateExtrude(Geometry2D geometry2D, Angle angle, int angularResolution, int convexity)
	{
		this.geometry2D = geometry2D;
		this.angle = angle;
		this.angularResolution = angularResolution;
		this.convexity = convexity;
	}

	public Geometry2D getGeometry2D()
	{
		return geometry2D;
	}

	public Angle getAngle()
	{
		return angle;
	}

	public int getAngularResolution()
	{
		return angularResolution;
	}

	public int getConvexity()
	{
		return convexity;
	}
}
