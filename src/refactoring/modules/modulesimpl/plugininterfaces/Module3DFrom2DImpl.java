package refactoring.modules.modulesimpl.plugininterfaces;
import refactoring.modules.modulesintf.CSG;
import refactoring.coreimpl.core.Module2D;
import refactoring.coreimpl.core.Module3D;
import refactoring.coreimpl.core.PluginModule;

import java.util.List;

public interface Module3DFrom2DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg, List<Module2D> children);
}
