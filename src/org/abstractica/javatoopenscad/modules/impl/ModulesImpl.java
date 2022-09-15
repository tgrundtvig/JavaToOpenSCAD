package org.abstractica.javatoopenscad.modules.impl;

import org.abstractica.javatoopenscad.modules.Modules;
import org.abstractica.javatoopenscad.scad.Coord2D;

public class ModulesImpl implements Modules
{
	@Override
	public UnitSquare2D unitSquare2D()
	{
		return (UnitSquare2D) UniqueModules.unique(new UnitSquare2D());
	}

	@Override
	public RectCenter2D rectCenter2D(double width, double height)
	{
		return (RectCenter2D) UniqueModules.unique(new RectCenter2D(width, height));
	}
	@Override
	public RectCorners2D rectCorners2D(Coord2D cornerA, Coord2D cornerB)
	{
		return (RectCorners2D) UniqueModules.unique(new RectCorners2D(cornerA, cornerB));
	}
}
