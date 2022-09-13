package org.abstractica.javatoopenscad.corescad.impl;

import org.abstractica.javatoopenscad.corescad.Geometry;

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
