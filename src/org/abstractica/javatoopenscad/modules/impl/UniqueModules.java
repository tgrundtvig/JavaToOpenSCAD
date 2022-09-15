package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.scad.module.AModule;

import java.util.HashMap;
import java.util.Map;

public class UniqueModules
{
	private final static Map<Integer, AModule> allModules = new HashMap<>();

	public static AModule unique(AModule newModule)
	{
		Integer id = newModule.getUniqueId();
		AModule unique = allModules.get(id);
		if(unique == null)
		{
			unique = newModule;
			allModules.put(id, unique);
		}
		return unique;
	}
}
