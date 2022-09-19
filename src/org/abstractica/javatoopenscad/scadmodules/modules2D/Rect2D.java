package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

public class Rect2D implements SCADModule2DImplementation
{
	public final double width;
	public final double height;

	public Rect2D(double width, double height)
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
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		Node2D scale = scad.getSCAD2D().scale2D(width, height);
		return scale.add(modules.unitSquare2D());
	}
}
