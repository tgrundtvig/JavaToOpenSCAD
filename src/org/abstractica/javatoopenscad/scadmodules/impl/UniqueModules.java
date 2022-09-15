package org.abstractica.javatoopenscad.scadmodules.impl;

import org.abstractica.javatoopenscad.scad.module.*;
import org.abstractica.javatoopenscad.scad.module.impl.ArgumentsImpl;
import org.abstractica.javatoopenscad.scad.module.impl.SCADModuleImpl;

import java.util.HashMap;
import java.util.Map;

public class UniqueModules
{
	private final static Map<Integer, SCADModule> allModules = new HashMap<>();

	private static SCADModule getUnique(SCADModuleImplementation newModule)
	{
		Integer id = new ArgumentsImpl(newModule).getUniqueId();
		SCADModule unique = allModules.get(id);
		if(unique == null)
		{
			unique = new SCADModuleImpl(newModule);
			allModules.put(id, unique);
		}
		return unique;
	}

	public static SCADModule2D unique(SCADModule2DImplementation newModule)
	{
		return (SCADModule2D) getUnique(newModule);
	}

	public static SCADModule3D unique(SCADModule3DImplementation newModule)
	{
		return (SCADModule3D) getUnique(newModule);
	}
}
