package org.abstractica.javatoopenscad.testing;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.SCADImpl;
import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test
{
	public static void main(String[] args) throws IOException
	{

		//Create core
		SCAD core = new SCADImpl();

		//Create geometry
		List<Coord2D> vertices = new ArrayList<>();
		vertices.add(Coord2D.ORIGO);
		vertices.add(Coord2D.polar2D(10, Angle.deg(0)));
		vertices.add(Coord2D.polar2D(10, Angle.deg(45)));
		vertices.add(Coord2D.polar2D(10, Angle.deg(90)));
		vertices.add(Coord2D.polar2D(10, Angle.deg(135)));

		Geometry2D polygon = core.getSCAD2D().polygon2D(vertices);

		//Output geometry
		OpenSCAD.generateOutput(polygon);
	}
}
