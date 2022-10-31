package org.abstractica.openscadcsg.test;

import org.abstractica.openscadcore.impl.operationsimpl.identifier.AllStrings;
import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcsg.impl.CSGImpl;
import org.abstractica.openscadcsg.intf.csg2D.CSG2DBase;

import java.io.IOException;

public class TestEllipse
{
	public static void main(String[] args) throws IOException
	{
		CSG2DBase gb = new CSGImpl(null);
		Geometry2D ellipse = gb.ellipse(gb.vector2D(10,20), gb.vector2D(0, -10), 128);
		//Geometry2D ellipse = gb.ellipse(0, 10, -10, 20, 128);
		gb.generateOpenSCADFile("OpenSCAD/output.scad", ellipse);
		System.out.println(AllStrings.listAllStrings());
	}
}
