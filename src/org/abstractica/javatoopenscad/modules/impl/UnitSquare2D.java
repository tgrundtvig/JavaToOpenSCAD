package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.module.AModule;
import org.abstractica.javatoopenscad.scad.module.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;

import java.util.ArrayList;
import java.util.List;

public class UnitSquare2D extends AModule implements Geometry2D
{
	@Override
	public void getArguments(ArgumentCollector collector)
	{

	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, Modules modules)
	{
		List<Coord2D> vertices = new ArrayList<>();
		vertices.add(Coord2D.vector(-0.5, -0.5));
		vertices.add(Coord2D.vector(0.5, -0.5));
		vertices.add(Coord2D.vector(0.5, 0.5));
		vertices.add(Coord2D.vector(-0.5, 0.5));
		return scad.getSCAD2D().polygon2D(vertices);
	}
}
