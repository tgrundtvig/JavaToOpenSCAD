package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scad.impl.impl3d.ANode3D;

public class ProjectedGeometry extends ANode3D implements Geometry2D
{
	private final Geometry3D geometry;
	private final boolean cut;

	public ProjectedGeometry(Geometry3D geometry, boolean cut)
	{
		this.geometry = geometry;
		this.cut = cut;
	}

	public Geometry3D getGeometry3D()
	{
		return geometry;
	}

	public boolean getCut()
	{
		return cut;
	}
}
