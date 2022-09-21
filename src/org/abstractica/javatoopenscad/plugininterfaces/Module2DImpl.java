package org.abstractica.javatoopenscad.plugininterfaces;


import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.PluginModule;

public interface Module2DImpl extends PluginModule
{
	Module2D buildGeometry(CSG csg);
}
