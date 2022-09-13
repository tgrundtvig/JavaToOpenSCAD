package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.corescad2d.Geometry2D;
import org.abstractica.javatoopenscad.corescad.corescad2d.Node2D;
import org.abstractica.javatoopenscad.corescad.impl.AGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ANode2D extends AGeometry implements Node2D
{
	private final List<Geometry2D> children;

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
	public Iterator<Geometry2D> iterator()
	{
		return Collections.unmodifiableList(children).iterator();
	}
}
