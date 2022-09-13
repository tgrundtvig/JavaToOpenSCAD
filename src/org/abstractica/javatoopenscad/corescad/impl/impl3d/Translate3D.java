package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.corescad3d.Coord3D;

public class Translate3D extends ANode3D
{
	private final Coord3D vec;

	public Translate3D(Coord3D vec)
	{
		this.vec = vec;
	}

	public Coord3D vec()
	{
		return vec;
	}
}
