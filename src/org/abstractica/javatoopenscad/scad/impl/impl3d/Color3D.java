package org.abstractica.javatoopenscad.scad.impl.impl3d;

import org.abstractica.javatoopenscad.scad.impl.Color;

public class Color3D extends ANode3D
{
	private final Color color;

	Color3D(Color color)
	{
		this.color = color;
	}

	public Color getColor()
	{
		return color;
	}
}
