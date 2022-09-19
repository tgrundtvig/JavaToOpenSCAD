package refactoring.modules.plugininterfaces;

import refactoring.modules.CSG;
import refactoring.core.Module3D;
import refactoring.modules.PluginModule;

public interface Module3DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg);
}
