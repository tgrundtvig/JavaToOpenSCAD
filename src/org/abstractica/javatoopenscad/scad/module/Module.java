package org.abstractica.javatoopenscad.scad.module;

import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;

public interface Module
{
	Geometry getDisplayGeometry(SCAD scad, Modules modules);
}
