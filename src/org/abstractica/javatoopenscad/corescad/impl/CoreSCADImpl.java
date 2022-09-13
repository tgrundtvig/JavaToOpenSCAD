package org.abstractica.javatoopenscad.corescad.impl;

import org.abstractica.javatoopenscad.corescad.*;
import org.abstractica.javatoopenscad.corescad.corescad2d.*;
import org.abstractica.javatoopenscad.corescad.corescad3d.*;
import org.abstractica.javatoopenscad.corescad.impl.impl2d.CoreSCAD2DImpl;
import org.abstractica.javatoopenscad.corescad.impl.impl3d.*;

public class CoreSCADImpl implements CoreSCAD
{
	private final CoreSCAD2D coreSCAD2D = new CoreSCAD2DImpl();
	private final CoreSCAD3D coreSCAD3D = new CoreSCAD3DImpl();

	@Override
	public CoreSCAD2D getSCAD2D()
	{
		return coreSCAD2D;
	}

	@Override
	public CoreSCAD3D getSCAD3D()
	{
		return coreSCAD3D;
	}


	@Override
	public Geometry3D linearExtrude(Geometry2D geometry2D, double height, Angle twist, double scale, int slices, int convexity)
	{
		return new LinearExtrude(geometry2D, height, twist, scale, slices, convexity);
	}

	@Override
	public Geometry3D rotateExtrude(Geometry2D geometry2D, Angle angle, int angularResolution, int convexity)
	{
		return new RotateExtrude(geometry2D, angle, angularResolution, convexity);
	}

	@Override
	public Geometry2D project(Geometry3D geometry3D, boolean cut)
	{
		return new ProjectedGeometry(geometry3D, cut);
	}
}
