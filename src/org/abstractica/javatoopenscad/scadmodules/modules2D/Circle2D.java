package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

public class Circle2D implements SCADModule2DImplementation
{
	private final double diameter;
	private final int angularResolution;

	public Circle2D(double diameter, int angularResolution)
	{
		this.diameter = diameter;
		this.angularResolution = angularResolution;
	}


	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameter", diameter);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		Node2D scale = scad.getSCAD2D().scale2D(diameter, diameter);
		scale.add(modules.unitCircle2D(angularResolution));
		return scale;
	}
}
