package org.abstractica.openscadcsg.test;

import org.abstractica.openscadcore.impl.operationsimpl.identifier.AllStrings;
import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcsg.impl.CSGImpl;
import org.abstractica.openscadcsg.intf.csg2D.CSG2DBase;

import java.io.IOException;

public class TestRectangle
{
	public static void main(String[] args) throws IOException
	{
		CSG2DBase gb = new CSGImpl();
		Geometry2D circle = gb.circle(10, 128);
		gb.generateOpenSCADFile("OpenSCAD/output.scad", circle);
		System.out.println(AllStrings.listAllStrings());
	}
}
