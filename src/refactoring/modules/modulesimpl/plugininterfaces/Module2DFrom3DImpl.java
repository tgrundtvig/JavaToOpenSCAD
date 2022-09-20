package refactoring.modules.modulesimpl.plugininterfaces;

import refactoring.coreimpl.core.Module2DFrom3D;
import refactoring.modules.modulesintf.CSG;
import refactoring.coreimpl.core.Module3D;
import refactoring.coreimpl.core.PluginModule;


import java.util.List;

public interface Module2DFrom3DImpl extends PluginModule
{
	Module2DFrom3D buildGeometry(CSG csg, List<Module3D> children);
}
