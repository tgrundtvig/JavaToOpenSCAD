package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;


import java.util.ArrayList;
import java.util.List;

public class UnitSquare2D implements SCADModule2DImplementation
{
	@Override
	public void getArguments(ArgumentCollector collector)
	{

	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		List<Coord2D> vertices = new ArrayList<>();
		vertices.add(Coord2D.vector2D(-0.5, -0.5));
		vertices.add(Coord2D.vector2D(0.5, -0.5));
		vertices.add(Coord2D.vector2D(0.5, 0.5));
		vertices.add(Coord2D.vector2D(-0.5, 0.5));
		return scad.getSCAD2D().polygon2D(vertices);
	}
}
