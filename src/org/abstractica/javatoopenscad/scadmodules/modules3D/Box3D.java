package org.abstractica.javatoopenscad.scadmodules.modules3D;

import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.scad3d.Node3D;
import org.abstractica.javatoopenscad.scadmodules.SCADModules;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.SCADModule3DImplementation;

public class Box3D implements SCADModule3DImplementation
{
	public final double xSize;
	public final double ySize;
	public final double zSize;

	public Box3D(double xSize, double ySize, double zSize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("xSize", xSize);
		collector.add("ySize", ySize);
		collector.add("zSize", zSize);
	}

	@Override
	public Geometry generateDisplayGeometry(SCAD scad, SCADModules modules)
	{
		Node3D scale = scad.getSCAD3D().scale3D(xSize, ySize, zSize);
		scale.add(modules.unitCube3D());
		return scale;
	}
}
