package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg3d.Arrange3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;

public class Arrange3DImpl extends AModuleFactory implements Arrange3D
{
	public Arrange3DImpl(CSGImpl csg)
	{
		super(csg);
	}
}
