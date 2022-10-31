package org.abstractica.openscadcore.impl;


import org.abstractica.openscadcore.intf.Geometry3D;
import org.abstractica.openscadcore.intf.Geometry3DFrom3D;

public abstract class AGeometry3DFrom3D extends AGeometryNode implements Geometry3DFrom3D
{
	protected AGeometry3DFrom3D() {}

	@Override
	public Geometry3DFrom3D add(Geometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
