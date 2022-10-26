package org.abstractica.javatoopenscad.coreimpl.core.impl;

import org.abstractica.javatoopenscad.coreimpl.core.*;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.*;

public abstract class AModuleFactory implements ModuleFactory
{
	private final CSGImpl csg;


	public AModuleFactory(CSGImpl csg)
	{
		this.csg = csg;
	}

	public CSG getCSG()
	{
		return csg;
	}

	public CSGImpl getCSGImpl()
	{
		return csg;
	}

	@Override
	public Module2D module2D(Module2DImpl impl)
	{
		return csg.module2D(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(Module2DFrom2DImpl impl)
	{
		return csg.module2DFrom2D(impl);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(Module2DFrom3DImpl impl)
	{
		return csg.module2DFrom3D(impl);
	}

	@Override
	public Module3D module3D(Module3DImpl impl)
	{
		return csg.module3D(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(Module3DFrom2DImpl impl)
	{
		return csg.module3DFrom2D(impl);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(Module3DFrom3DImpl impl)
	{
		return csg.module3DFrom3D(impl);
	}

	@Override
	public Module2D module2D(BuiltInModule impl)
	{
		return csg.module2D(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(BuiltInModule impl)
	{
		return csg.module2DFrom2D(impl);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(BuiltInModule impl)
	{
		return csg.module2DFrom3D(impl);
	}

	@Override
	public Module3D module3D(BuiltInModule impl)
	{
		return csg.module3D(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(BuiltInModule impl)
	{
		return csg.module3DFrom2D(impl);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(BuiltInModule impl)
	{
		return csg.module3DFrom3D(impl);
	}
}