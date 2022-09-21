package org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces;

import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

import java.util.List;

public interface Module3DFrom3DImpl extends PluginModule
{
	Module3DFrom3D buildGeometry(CSG csg, List<Module3D> children);
}
