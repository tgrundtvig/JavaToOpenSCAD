package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg2d.Color2D;
import org.abstractica.javatoopenscad.modulesimpl.common.Color;

public class Color2DImpl extends AModuleFactory implements Color2D
{
	public Color2DImpl(ModuleFactory factory)
	{
		super(factory);
	}
	@Override
	public Module2DFrom2D color2D(Color color)
	{
		return module2DFrom2D(color);
	}
}
