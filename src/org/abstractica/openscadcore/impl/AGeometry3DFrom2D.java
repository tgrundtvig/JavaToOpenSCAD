package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcore.intf.Geometry3DFrom2D;

public abstract class AGeometry3DFrom2D extends AGeometryNode implements Geometry3DFrom2D
{
	protected AGeometry3DFrom2D() {}

	@Override
	public Geometry3DFrom2D add(Geometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
