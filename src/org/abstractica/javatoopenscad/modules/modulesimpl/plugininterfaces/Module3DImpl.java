package org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces;

import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

public interface Module3DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg);
}
