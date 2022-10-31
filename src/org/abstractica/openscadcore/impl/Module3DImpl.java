package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.intf.Geometry3D;

public class Module3DImpl extends AModule implements Geometry3D
{

	public Module3DImpl(Geometry3D geometry, int id)
	{
		super((AGeometry) geometry, id);
	}
}
