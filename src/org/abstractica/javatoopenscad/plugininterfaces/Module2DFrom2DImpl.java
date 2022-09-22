package org.abstractica.javatoopenscad.plugininterfaces;

import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

import java.util.List;

public interface Module2DFrom2DImpl extends PluginModule
{
	Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children);
}
