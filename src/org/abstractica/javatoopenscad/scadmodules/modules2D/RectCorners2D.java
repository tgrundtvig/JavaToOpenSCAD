package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

public class RectCorners2D implements SCADModule2DImplementation
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
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		double width = Math.abs(cornerA.x()- cornerB.x());
		double height = Math.abs(cornerA.y() - cornerB.y());
		double x = 0.5*(cornerA.x()+cornerB.x());
		double y = 0.5*(cornerA.y()+cornerB.y());
		Node2D translate = scad.getSCAD2D().translate2D(Coord2D.vector2D(x,y));
		translate.add(modules.rect2D(width, height));
		return translate;
	}
}
