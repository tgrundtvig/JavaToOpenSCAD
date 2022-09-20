package refactoring.modules.modulesimpl.plugininterfaces;

import refactoring.modules.modulesintf.CSG;
import refactoring.coreimpl.core.Module3D;
import refactoring.coreimpl.core.PluginModule;

public interface Module3DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg);
}
