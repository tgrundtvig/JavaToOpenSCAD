package org.abstractica.javatoopenscad.scadmodules.impl;

import org.abstractica.javatoopenscad.scad.module.SCADModule3D;
import org.abstractica.javatoopenscad.scad.scad3d.Coord3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.module.SCADModule2D;
import org.abstractica.javatoopenscad.scadmodules.modules2D.*;
import org.abstractica.javatoopenscad.scadmodules.modules3D.Box3D;
import org.abstractica.javatoopenscad.scadmodules.modules3D.BoxCorners3D;
import org.abstractica.javatoopenscad.scadmodules.modules3D.UnitCube3D;

public class SCADModulesImpl implements SCADModules
{
	@Override
	public SCADModule2D unitCircle2D(int angularResolution)
	{
		return UniqueModules.unique(new UnitCircle2D(angularResolution));
	}

	@Override
	public SCADModule2D circle2D(double diameter, int angularResolution)
	{
		return UniqueModules.unique(new Circle2D(diameter, angularResolution));
	}

	@Override
	public SCADModule2D ellipse2D(double diameterX, double diameterY, int angularResolution)
	{
		return UniqueModules.unique(new Ellipse2D(diameterX, diameterY, angularResolution));
	}

	@Override
	public SCADModule2D unitSquare2D()
	{
		return UniqueModules.unique(new UnitSquare2D());
	}

	@Override
	public SCADModule2D rect2D(double width, double height)
	{
		return UniqueModules.unique(new Rect2D(width, height));
	}
	@Override
	public SCADModule2D rectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		return UniqueModules.unique(new RectCorners2D(cornerA, cornerB));
	}

	@Override
	public SCADModule2D lineOf(SCADModule2D module, int count, double distance)
	{
		return UniqueModules.unique(new LineOf2D(module, count, distance));
	}

	@Override
	public SCADModule3D unitCube3D()
	{
		return UniqueModules.unique(new UnitCube3D());
	}

	@Override
	public SCADModule3D box3D(double xSize, double ySize, double zSize)
	{
		return UniqueModules.unique(new Box3D(xSize, ySize, zSize));
	}

	@Override
	public SCADModule3D boxCorners3D(Coord3D cornerA, Coord3D cornerB)
	{
		return UniqueModules.unique(new BoxCorners3D(cornerA, cornerB));
	}
}
