package refactoring.modules.plugininterfaces;

import refactoring.core.Module2DFrom3D;
import refactoring.modules.CSG;
import refactoring.core.Module3D;
import refactoring.modules.PluginModule;


import java.util.List;

public interface Module2DFrom3DImpl extends PluginModule
{
	Module2DFrom3D buildGeometry(CSG csg, List<Module3D> children);
}
