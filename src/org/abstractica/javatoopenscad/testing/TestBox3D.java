package org.abstractica.javatoopenscad.testing;

import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModulesImpl;

import java.io.IOException;

public class TestBox3D
{
	public static void main(String[] args) throws IOException
	{
		//Create core
		SCAD core = new SCADImpl();

		//Create module factory
		SCADModules modules = new SCADModulesImpl();

		//Create geometry
		Geometry3D box = modules.box3D(10,20,30);




		//Output geometry
		OpenSCAD.generateOutput(box);
	}
}
