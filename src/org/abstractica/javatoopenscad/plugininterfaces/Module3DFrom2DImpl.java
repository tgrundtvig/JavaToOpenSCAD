package org.abstractica.javatoopenscad.plugininterfaces;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

import java.util.List;

public interface Module3DFrom2DImpl extends PluginModule
{
	Module3D buildGeometry(CSG csg, List<Module2D> children);
}
