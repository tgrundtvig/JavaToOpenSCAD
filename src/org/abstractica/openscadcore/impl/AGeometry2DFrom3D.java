package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.intf.Geometry2DFrom3D;
import org.abstractica.openscadcore.intf.Geometry3D;

public abstract class AGeometry2DFrom3D extends AGeometryNode implements Geometry2DFrom3D
{
	protected AGeometry2DFrom3D() {}

	@Override
	public Geometry2DFrom3D add(Geometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
