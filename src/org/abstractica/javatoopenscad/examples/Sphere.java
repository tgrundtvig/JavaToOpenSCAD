package org.abstractica.javatoopenscad.examples;

import org.abstractica.javatoopenscad.corescad.CoreSCAD;
import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;
import org.abstractica.javatoopenscad.corescad.impl.CoreSCADImpl;
import org.abstractica.javatoopenscad.generators.openscad.OpenSCAD;

import java.io.IOException;

public class Sphere
{
	public static void main(String[] args) throws IOException
	{

		//Create core
		CoreSCAD core = new CoreSCADImpl();

		//Create geometry
		Geometry3D sphere = core.getSCAD3D().sphere3D(10,128);

		//Output geometry
		OpenSCAD.generateOutput(sphere);

		System.out.println(Math.asin(1));
		System.out.println(Math.PI / 2);
	}
}
