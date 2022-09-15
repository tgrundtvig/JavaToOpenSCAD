package org.abstractica.javatoopenscad.examples;

import org.abstractica.javatoopenscad.modules.impl.ModulesImpl;
import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.modules.impl.RectCorners2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;

import java.io.IOException;

public class TestModules
{
	public static void main(String[] args) throws IOException
	{

		//Create core
		SCAD core = new SCADImpl();

		//Create module factory
		Modules modules = new ModulesImpl();

		//Create geometry
		Coord2D cornerA = Coord2D.ORIGO;
		Coord2D cornerB = Coord2D.vector(20,15);
		RectCorners2D rect1 = modules.rectCorners2D(cornerA, cornerB);
		cornerB = Coord2D.vector(-20,-15);
		RectCorners2D rect2 = modules.rectCorners2D(cornerA, cornerB);
		Node2D union = core.getSCAD2D().union2D();
		union.add(rect1).add(rect2);
		//Output geometry
		OpenSCAD.generateOutput(union);
	}
}
