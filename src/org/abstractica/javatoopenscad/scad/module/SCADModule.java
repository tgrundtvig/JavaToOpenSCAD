package org.abstractica.javatoopenscad.scad.module;

import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;

public interface SCADModule extends Geometry, Arguments
{
	Geometry getDisplayGeometry(SCAD scad, SCADModules modules);
}
