package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.Coord2D;

public class Translate2D extends ANode2D
{
	private final Coord2D vec;

	public Translate2D(Coord2D vec)
	{
		this.vec = vec;
	}

	public Coord2D vec()
	{
		return vec;
	}
}
