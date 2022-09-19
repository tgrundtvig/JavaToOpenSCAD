package org.abstractica.javatoopenscad.scadmodules.modules2D;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule2DImplementation;

import java.util.ArrayList;

public class UnitCircle2D implements SCADModule2DImplementation
{
	private final int angularResolution;

	public UnitCircle2D(int angularResolution)
	{
		if(angularResolution < 3)
		{
			throw new RuntimeException("Angular resolution must be at least 3.");
		}
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		double segmentAngle = 1.0 / angularResolution;
		ArrayList<Coord2D> points = new ArrayList<>();
		for(int i = 0; i < angularResolution; ++i)
		{
			points.add(Coord2D.polar2D(1,Angle.rot(i*segmentAngle)));
		}
		return scad.getSCAD2D().polygon2D(points);
	}
}
