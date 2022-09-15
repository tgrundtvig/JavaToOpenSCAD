package org.abstractica.javatoopenscad.scad.impl.impl3d;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scad.scad3d.Node3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ANode3D implements Node3D
{
	private final List<Geometry> children;

	public ANode3D()
	{
		children = new ArrayList<>(1);
	}

	@Override
	public Node3D add(Geometry3D geometry3D)
	{
		children.add(geometry3D);
		return this;
	}

	@Override
	public Iterator<Geometry> iterator()
	{
		return Collections.unmodifiableList(children).iterator();
	}
}
