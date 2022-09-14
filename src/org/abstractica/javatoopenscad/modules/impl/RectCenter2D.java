package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.impl.AModule;

import java.util.ArrayList;
import java.util.List;

public class RectCenter2D extends AModule implements Geometry2D
{
	public final double width;
	public final double height;

	public RectCenter2D(double width, double height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public void getParameters(ParameterCollector collector)
	{
		collector.add("width", width);
		collector.add("height", height);
	}

	@Override
	public Geometry generateGeometry(SCAD scad)
	{
		List<Coord2D> vertices = new ArrayList<>();
		vertices.add(Coord2D.vector(-0.5*width, -0.5*height));
		vertices.add(Coord2D.vector(0.5*width, -0.5*height));
		vertices.add(Coord2D.vector(0.5*width, 0.5*height));
		vertices.add(Coord2D.vector(-0.5*width, 0.5*height));
		return scad.getSCAD2D().polygon2D(vertices);
	}
}
