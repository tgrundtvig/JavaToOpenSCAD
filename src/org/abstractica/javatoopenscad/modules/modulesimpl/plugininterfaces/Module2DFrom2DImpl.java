package org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces;

import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

import java.util.List;

public interface Module2DFrom2DImpl extends PluginModule
{
	Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children);
}
