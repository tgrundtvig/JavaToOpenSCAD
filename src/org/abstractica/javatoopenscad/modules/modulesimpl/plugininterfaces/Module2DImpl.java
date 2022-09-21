package org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces;


import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

public interface Module2DImpl extends PluginModule
{
	Module2D buildGeometry(CSG csg);
}
