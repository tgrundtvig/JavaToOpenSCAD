package refactoring.modules.modulesimpl.plugininterfaces;

import refactoring.coreimpl.core.Module2DFrom2D;
import refactoring.modules.modulesintf.CSG;
import refactoring.coreimpl.core.Module2D;
import refactoring.coreimpl.core.PluginModule;

import java.util.List;

public interface Module2DFrom2DImpl extends PluginModule
{
	Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children);
}
