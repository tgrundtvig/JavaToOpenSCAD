package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d.Cylinder3D;

public class Shapes3DImpl extends AModuleFactory implements Shapes3D
{
	public Shapes3DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module3D cylinder3D(double diameter, double height, int angularResolution)
	{
		return module3D(new Cylinder3D(diameter, height, angularResolution));
	}
}
