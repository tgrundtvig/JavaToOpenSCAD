package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.corescad2d.Coord2D;

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
