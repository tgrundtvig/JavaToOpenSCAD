package org.abstractica.javatoopenscad.testing;

import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModulesImpl;

import java.io.IOException;

public class TestCircle
{
	public static void main(String[] args) throws IOException
	{
		//Create core
		SCAD core = new SCADImpl();

		//Create module factory
		SCADModules modules = new SCADModulesImpl();

		//Create geometry
		Geometry2D circle = modules.circle2D(20, 64);

		//Output geometry
		OpenSCAD.generateOutput(circle);
	}
}
