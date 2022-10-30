package org.abstractica.openbuildsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.CSG;

public abstract class APrint3DGeometry
{
	private final CSG csg;
	private final Print3DAdjust adjust;


	protected APrint3DGeometry(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}

	protected Module2DFrom2D translate2D(double x, double y)
	{
		return csg.csg2D().construct2D().translate2D(x, y);
	}

	protected Module3DFrom3D translate3D(double x, double y, double z)
	{
		return csg.csg3D().construct3D().translate3D(x, y, z);
	}
}
