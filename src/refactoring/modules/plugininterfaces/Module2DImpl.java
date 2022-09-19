package refactoring.modules.plugininterfaces;


import refactoring.core.Module2D;
import refactoring.modules.CSG;
import refactoring.modules.PluginModule;

public interface Module2DImpl extends PluginModule
{
	Module2D buildGeometry(CSG csg);
}
