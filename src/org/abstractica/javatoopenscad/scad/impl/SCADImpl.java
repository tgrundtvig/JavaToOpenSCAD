package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.*;
import org.abstractica.javatoopenscad.scad.scad2d.*;
import org.abstractica.javatoopenscad.scad.scad3d.*;
import org.abstractica.javatoopenscad.scad.impl.impl2d.SCAD2DImpl;
import org.abstractica.javatoopenscad.scad.impl.impl3d.*;

public class SCADImpl implements SCAD
{
	private final SCAD2D scad2D = new SCAD2DImpl();
	private final SCAD3D scad3D = new SCAD3DImpl();

	@Override
	public SCAD2D getSCAD2D()
	{
		return scad2D;
	}

	@Override
	public SCAD3D getSCAD3D()
	{
		return scad3D;
	}


	@Override
	public Node2DToGeometry3D linearExtrude(Geometry2D geometry2D, double height, Angle twist, double scale, int slices, int convexity)
	{
		return new LinearExtrude(geometry2D, height, twist, scale, slices, convexity);
	}

	@Override
	public Node2DToGeometry3D rotateExtrude(Geometry2D geometry2D, Angle angle, int angularResolution, int convexity)
	{
		return new RotateExtrude(geometry2D, angle, angularResolution, convexity);
	}

	@Override
	public Geometry2D project(Geometry3D geometry3D, boolean cut)
	{
		return new ProjectedGeometry(geometry3D, cut);
	}
}
