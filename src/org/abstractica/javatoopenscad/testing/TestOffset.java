package org.abstractica.javatoopenscad.testing;

import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModulesImpl;

import java.io.IOException;

public class TestOffset
{
	public static void main(String[] args) throws IOException
	{
		//Create core
		SCAD core = new SCADImpl();

		//Create module factory
		SCADModules modules = new SCADModulesImpl();

		//Create geometry
		Geometry2D circle = modules.circle2D(20, 64);
		Geometry2D rect = modules.rectCorners2D(Coord2D.ORIGO, Coord2D.vector2D(20, 20));
		Node2D diff = core.getSCAD2D().difference2D().add(circle).add(rect);
		Node2D offset = core.getSCAD2D().offset2D(-2, true).add(diff);
		//Output geometry
		OpenSCAD.generateOutput(offset);
	}
}
