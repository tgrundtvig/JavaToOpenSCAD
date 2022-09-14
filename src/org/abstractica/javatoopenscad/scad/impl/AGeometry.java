package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.Geometry;

public class AGeometry implements Geometry
{
	private boolean marked = false;

	@Override
	public void debugMark()
	{
		marked = true;
	}

	@Override
	public boolean isDebugMarked()
	{
		return marked;
	}
}
