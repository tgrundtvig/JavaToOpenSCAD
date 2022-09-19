package refactoring.modulesimpl;

import refactoring.core.*;
import refactoring.modules.PluginModule;

import java.util.Map;

public class AModuleFactory implements ModuleFactory
{
	private final ModuleFactory factory;

	public AModuleFactory(ModuleFactory factory)
	{
		this.factory = factory;
	}

	@Override
	public Module2D module2D(PluginModule plugInModule)
	{
		return factory.module2D(plugInModule);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(PluginModule plugInModule)
	{
		return factory.module2DFrom2D(plugInModule);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(PluginModule plugInModule)
	{
		return factory.module2DFrom3D(plugInModule);
	}

	@Override
	public Module3D module3D(PluginModule plugInModule)
	{
		return factory.module3D(plugInModule);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(PluginModule plugInModule)
	{
		return factory.module3DFrom2D(plugInModule);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(PluginModule plugInModule)
	{
		return factory.module3DFrom3D(plugInModule);
	}
}
