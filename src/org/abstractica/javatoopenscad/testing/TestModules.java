package org.abstractica.javatoopenscad.testing;

import org.abstractica.javatoopenscad.scadmodules.impl.SCADModulesImpl;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;

import java.io.IOException;

public class TestModules
{
	public static void main(String[] args) throws IOException
	{

		//Create core
		SCAD core = new SCADImpl();

		//Create module factory
		SCADModules modules = new SCADModulesImpl();

		//Create geometry
		Coord2D cornerA = Coord2D.ORIGO;
		Coord2D cornerB = Coord2D.vector2D(40,15);
		SCADModule2D rect1 = modules.rectCorners2D(cornerA, cornerB);
		cornerB = Coord2D.vector2D(-20,-15);
		SCADModule2D rect2 = modules.rectCorners2D(cornerA, cornerB);
		Node2D union = core.getSCAD2D().union2D();
		union.add(rect1).add(rect2);
		//Output geometry
		OpenSCAD.generateOutput(union);
	}
}
