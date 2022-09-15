package org.abstractica.javatoopenscad.scadmodules.impl;

import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;

public interface SCADModuleImplementation extends ArgumentsImplementation
{
	Geometry generateDisplayGeometry(SCAD scad, SCADModules modules);
}
