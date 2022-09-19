package refactoring.modules.plugininterfaces;
import refactoring.modules.CSG;
import refactoring.core.Module2D;
import refactoring.core.Module3D;
import refactoring.modules.PluginModule;

import java.util.List;

public interface Module3DFrom2DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg, List<Module2D> children);
}
