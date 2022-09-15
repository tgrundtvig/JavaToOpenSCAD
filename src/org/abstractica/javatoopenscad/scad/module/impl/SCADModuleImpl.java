package org.abstractica.javatoopenscad.scad.module.impl;

import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.module.*;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModuleImplementation;

public class SCADModuleImpl implements SCADModule2D, SCADModule3D
{
	private final ArgumentsImpl arguments;
	private final SCADModuleImplementation moduleImplementation;
	private Geometry geometry = null;

	public SCADModuleImpl(SCADModuleImplementation impl)
	{
		moduleImplementation = impl;
		arguments = new ArgumentsImpl(impl);
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		arguments.getArguments(collector);
	}

	@Override
	public String getClearText()
	{
		return arguments.getClearText();
	}

	@Override
	public int getUniqueId()
	{
		return arguments.getUniqueId();
	}

	@Override
	public final Geometry getDisplayGeometry(SCAD scad, SCADModules modules)
	{
		if(geometry == null)
		{
			geometry = moduleImplementation.generateDisplayGeometry(scad, modules);
		}
		return geometry;
	}
}
