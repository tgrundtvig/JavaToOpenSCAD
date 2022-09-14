package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scad.scad2d.Vector2D;
import org.abstractica.javatoopenscad.scad.impl.AModule;
import org.abstractica.javatoopenscad.modules.Modules;

public class RectCorners2D extends AModule
{
	public final Coord2D cornerA;
	public final Coord2D cornerB;

	public RectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		this.cornerA = cornerA;
		this.cornerB = cornerB;
	}

	@Override
	public void getParameters(ParameterCollector collector)
	{
		collector.add("cornerA", cornerA.asVector2D());
		collector.add("cornerB", cornerB.asVector2D());
	}

	@Override
	public Geometry generateGeometry(SCAD scad)
	{
		Vector2D a = cornerA.asVector2D();
		Vector2D b = cornerB.asVector2D();
		double width = Math.abs(a.x()- b.x());
		double height = Math.abs(a.y() - b.y());
		double x = 0.5*(a.x()+b.x());
		double y = 0.5*(a.y()+b.y());
		Node2D translate = scad.getSCAD2D().translate2D(Coord2D.vector(x,y));
		translate.add(Modules.rectCenter2D(width, height));
		return translate;
	}
}
