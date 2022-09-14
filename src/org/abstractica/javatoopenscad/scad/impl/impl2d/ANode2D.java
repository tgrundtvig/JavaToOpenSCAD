package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scad.impl.AGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ANode2D extends AGeometry implements Node2D
{
	private final List<Geometry> children;

	public ANode2D()
	{
		children = new ArrayList<>(1);
	}

	@Override
	public Node2D add(Geometry2D geometry2D)
	{
		children.add(geometry2D);
		return this;
	}

	@Override
	public Iterator<Geometry> iterator()
	{
		return Collections.unmodifiableList(children).iterator();
	}
}
