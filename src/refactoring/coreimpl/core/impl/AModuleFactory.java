package refactoring.coreimpl.core.impl;

import refactoring.coreimpl.core.*;
import refactoring.modules.modulesimpl.plugininterfaces.*;

public class AModuleFactory implements ModuleFactory
{
	private final ModuleFactory factory;


	public AModuleFactory(ModuleFactory factory)
	{
		this.factory = factory;
	}

	@Override
	public Module2D module2D(Module2DImpl impl)
	{
		return factory.module2D(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(Module2DFrom2DImpl impl)
	{
		return factory.module2DFrom2D(impl);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(Module2DFrom3DImpl impl)
	{
		return factory.module2DFrom3D(impl);
	}

	@Override
	public Module3D module3D(Module3DImpl impl)
	{
		return factory.module3D(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(Module3DFrom2DImpl impl)
	{
		return factory.module3DFrom2D(impl);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(Module3DFrom3DImpl impl)
	{
		return factory.module3DFrom3D(impl);
	}

	@Override
	public Module2D module2D(BuiltInModule impl)
	{
		return factory.module2D(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(BuiltInModule impl)
	{
		return factory.module2DFrom2D(impl);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(BuiltInModule impl)
	{
		return factory.module2DFrom3D(impl);
	}

	@Override
	public Module3D module3D(BuiltInModule impl)
	{
		return factory.module3D(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(BuiltInModule impl)
	{
		return factory.module3DFrom2D(impl);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(BuiltInModule impl)
	{
		return factory.module3DFrom3D(impl);
	}
}
