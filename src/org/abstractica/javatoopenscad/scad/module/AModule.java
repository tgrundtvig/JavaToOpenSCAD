package org.abstractica.javatoopenscad.scad.module;

import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;

public abstract class AModule extends AArguments implements Module, Geometry
{
	private boolean marked = false;
	private Geometry geometry = null;

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

	@Override
	public abstract void getArguments(ArgumentCollector collector);

	public abstract Geometry generateDisplayGeometry(SCAD scad, Modules modules);

	@Override
	public final Geometry getDisplayGeometry(SCAD scad, Modules modules)
	{
		if(geometry == null)
		{
			geometry = generateDisplayGeometry(scad, modules);
		}
		return geometry;
	}
}
