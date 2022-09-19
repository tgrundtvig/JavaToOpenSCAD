package org.abstractica.javatoopenscad.scadmodules.modules3D;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad3d.Coord3D;
import org.abstractica.javatoopenscad.scad.scad3d.Geometry3D;
import org.abstractica.javatoopenscad.scad.scad3d.Node3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule3DImplementation;

import java.nio.charset.CoderResult;

public class BoxCorners3D implements SCADModule3DImplementation
{
	private final Coord3D cornerA;
	private final Coord3D cornerB;

	public BoxCorners3D(Coord3D cornerA, Coord3D cornerB)
	{
		this.cornerA = cornerA;
		this.cornerB = cornerB;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("cornerA", cornerA);
		collector.add("cornerB", cornerB);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		double xSize = Math.abs(cornerA.x() - cornerB.x());
		double ySize = Math.abs(cornerA.y() - cornerB.y());
		double zSize = Math.abs(cornerA.z() - cornerB.z());
		double x = 0.5*(cornerA.x() + cornerB.x());
		double y = 0.5*(cornerA.y() + cornerB.y());
		double z = 0.5*(cornerA.z() + cornerB.z());
		Geometry3D box = modules.box3D(xSize, ySize, zSize);
		Node3D translate = scad.getSCAD3D().translate3D(Coord3D.vector3D(x, y, z));
		translate.add(box);
		return translate;
	}
}
