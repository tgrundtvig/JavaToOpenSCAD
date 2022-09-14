package org.abstractica.javatoopenscad.examples;

import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;
import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.modules.impl.RectCorners2D;

import java.io.IOException;

public class TestModules
{
	public static void main(String[] args) throws IOException
	{

		//Create core
		SCAD core = new SCADImpl();

		//Create geometry
		Coord2D cornerA = Coord2D.ORIGO;
		Coord2D cornerB = Coord2D.vector(10,10);
		RectCorners2D rect = Modules.rectCorners2D(cornerA, cornerB);


		//Output geometry
		OpenSCAD.generateOutput(rect);
	}
}
