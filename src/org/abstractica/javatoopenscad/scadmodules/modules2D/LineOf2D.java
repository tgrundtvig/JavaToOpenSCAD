package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

public class LineOf2D implements SCADModule2DImplementation
{
	private final SCADModule2D module;
	private final int count;
	private final double distance;

	public LineOf2D(SCADModule2D module, int count, double distance)
	{
		this.module = module;
		this.count = count;
		this.distance = distance;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("module", module);
		collector.add("count", count);
		collector.add("distance", distance);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		Node2D res = scad.getSCAD2D().union2D();
		double start = -0.5*((count-1) * distance);
		for(int i = 0; i < count; ++i)
		{
			Node2D translate = scad.getSCAD2D().translate2D(Coord2D.vector2D(start + (i*distance), 0));
			translate.add(module);
			res.add(translate);
		}
		return res;
	}
}
