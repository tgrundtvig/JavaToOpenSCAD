package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.module.AModule;
import org.abstractica.javatoopenscad.scad.module.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;

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
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("width", width);
		collector.add("height", height);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, Modules modules)
	{

		Node2D scale = scad.getSCAD2D().scale2D(width, height);
		return scale.add(modules.unitSquare2D());
	}
}
