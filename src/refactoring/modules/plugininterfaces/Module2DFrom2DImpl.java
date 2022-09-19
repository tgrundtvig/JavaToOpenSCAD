package refactoring.modules.plugininterfaces;

import refactoring.core.Module2DFrom2D;
import refactoring.modules.CSG;
import refactoring.core.Module2D;
import refactoring.modules.PluginModule;

import java.util.List;

public interface Module2DFrom2DImpl extends PluginModule
{
	Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children);
}
