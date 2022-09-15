package org.abstractica.javatoopenscad.scadmodules.modules3D;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scad.scad2d.Node2D;
import org.abstractica.javatoopenscad.scad.scad3d.Node3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule3DImplementation;

public class UnitCube3D implements SCADModule3DImplementation
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		SCADModule2D unitSquare = modules.unitSquare2D();
		Node2D extrude = scad.linearExtrude(unitSquare,1, Angle.ZERO,1,1,2);
		extrude.add(unitSquare);
		return extrude;
	}
}
