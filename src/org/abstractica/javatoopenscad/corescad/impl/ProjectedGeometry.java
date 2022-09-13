package org.abstractica.javatoopenscad.corescad.impl;

import org.abstractica.javatoopenscad.corescad.corescad2d.Geometry2D;
import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;

public class ProjectedGeometry extends AGeometry implements Geometry2D
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
