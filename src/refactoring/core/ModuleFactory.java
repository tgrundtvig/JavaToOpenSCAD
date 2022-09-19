package refactoring.core;

import refactoring.modules.PluginModule;

public interface ModuleFactory
{
	Module2D module2D(PluginModule plugInModule);
	Module2DFrom2D module2DFrom2D(PluginModule plugInModule);
	Module2DFrom3D module2DFrom3D(PluginModule plugInModule);
	Module3D module3D(PluginModule plugInModule);
	Module3DFrom2D module3DFrom2D(PluginModule plugInModule);
	Module3DFrom3D module3DFrom3D(PluginModule plugInModule);
}
