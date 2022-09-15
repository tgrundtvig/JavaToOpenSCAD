package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.module.AModule;
import org.abstractica.javatoopenscad.scad.module.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.scad2d.Geometry2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.modules.Modules;

public class RectCorners2D extends AModule implements Geometry2D
{
	public final Coord2D cornerA;
	public final Coord2D cornerB;

	public RectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		this.cornerA = cornerA;
		this.cornerB = cornerB;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("cornerA", cornerA);
		collector.add("cornerB", cornerB);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, Modules modules)
	{
		double width = Math.abs(cornerA.x()- cornerB.x());
		double height = Math.abs(cornerA.y() - cornerB.y());
		double x = 0.5*(cornerA.x()+cornerB.x());
		double y = 0.5*(cornerA.y()+cornerB.y());
		Node2D translate = scad.getSCAD2D().translate2D(Coord2D.vector(x,y));
		translate.add(modules.rectCenter2D(width, height));
		return translate;
	}
}
