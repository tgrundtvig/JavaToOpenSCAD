package org.abstractica.javatoopenscad.scadmodules.impl;

import org.abstractica.javatoopenscad.scad.module.SCADModule3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.Coord2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scadmodules.modules2D.RectCenter2D;
import org.abstractica.javatoopenscad.scadmodules.modules2D.RectCorners2D;
import org.abstractica.javatoopenscad.scadmodules.modules2D.UnitSquare2D;
import org.abstractica.javatoopenscad.scadmodules.modules3D.UnitCube3D;

public class SCADModulesImpl implements SCADModules
{
	@Override
	public SCADModule2D unitSquare2D()
	{
		return UniqueModules.unique(new UnitSquare2D());
	}

	@Override
	public SCADModule2D rectCenter2D(double width, double height)
	{
		return UniqueModules.unique(new RectCenter2D(width, height));
	}
	@Override
	public SCADModule2D rectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		return UniqueModules.unique(new RectCorners2D(cornerA, cornerB));
	}

	@Override
	public SCADModule3D unitCube3D()
	{
		return UniqueModules.unique(new UnitCube3D());
	}
}
