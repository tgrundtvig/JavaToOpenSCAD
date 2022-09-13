package org.abstractica.javatoopenscad.corescad.impl.impl2d;

import org.abstractica.javatoopenscad.corescad.Color;

public class Color2D extends ANode2D
{
	public final Color color;

	Color2D(Color color)
	{
		this.color = color;
	}

	public Color getColor()
	{
		return color;
	}
}
