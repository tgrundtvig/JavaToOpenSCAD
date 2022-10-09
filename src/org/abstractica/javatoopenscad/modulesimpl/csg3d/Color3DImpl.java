package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg3d.Color3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.common.Color;

public class Color3DImpl extends AModuleFactory implements Color3D
{
	public Color3DImpl(CSGImpl csg)
	{
		super(csg);
	}

	@Override
	public Module3DFrom3D color3D(Color color)
	{
		return module3DFrom3D(color);
	}
}
