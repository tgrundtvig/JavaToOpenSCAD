package refactoring.modules.modulesimpl.plugininterfaces;


import refactoring.coreimpl.core.Module2D;
import refactoring.modules.modulesintf.CSG;
import refactoring.coreimpl.core.PluginModule;

public interface Module2DImpl extends PluginModule
{
	Module2D buildGeometry(CSG csg);
}
