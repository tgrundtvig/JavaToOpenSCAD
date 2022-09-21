package org.abstractica.javatoopenscad.plugininterfaces;

import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;


import java.util.List;

public interface Module2DFrom3DImpl extends PluginModule
{
	Module2DFrom3D buildGeometry(CSG csg, List<Module3D> children);
}
