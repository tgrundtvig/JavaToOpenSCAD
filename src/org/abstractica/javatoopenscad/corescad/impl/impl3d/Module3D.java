package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;
import org.abstractica.javatoopenscad.corescad.impl.AModule;

public class Module3D extends AModule implements Geometry3D
{
	private final Geometry3D geometry3D;

	public Module3D(String name, Geometry3D geometry3D)
	{
		super(name);
		this.geometry3D = geometry3D;
	}
	public Geometry3D getGeometry3D()
	{
		return geometry3D;
	}
}
