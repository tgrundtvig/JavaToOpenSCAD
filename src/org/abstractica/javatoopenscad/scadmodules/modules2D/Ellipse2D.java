package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

public class Ellipse2D implements SCADModule2DImplementation
{
	private final int angularResolution;
	private final double diameterX, diameterY;

	public Ellipse2D(double diameterX, double diameterY, int angularResolution)
	{
		this.diameterX = diameterX;
		this.diameterY = diameterY;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameterX", diameterX);
		collector.add("diameterY", diameterY);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		Node2D scale = scad.getSCAD2D().scale2D(diameterX, diameterY);
		scale.add(modules.unitCircle2D(angularResolution));
		return scale;
	}
}
