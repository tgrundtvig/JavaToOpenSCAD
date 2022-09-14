package org.abstractica.javatoopenscad.generators;

import org.abstractica.javatoopenscad.scad.Geometry;

public interface Generator
{
	void generate(CodeBuilder cb, Geometry geometry);
}
