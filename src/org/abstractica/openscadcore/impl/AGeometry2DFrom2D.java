package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcore.intf.Geometry2DFrom2D;

public abstract class AGeometry2DFrom2D extends AGeometryNode implements Geometry2DFrom2D
{
	protected AGeometry2DFrom2D() {}

	@Override
	public Geometry2DFrom2D add(Geometry2D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
