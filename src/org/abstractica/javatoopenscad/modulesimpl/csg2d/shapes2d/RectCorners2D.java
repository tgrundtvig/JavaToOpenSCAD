package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;

public class RectCorners2D implements Module2DImpl
{
	public final Vector2D cornerA;
	public final Vector2D cornerB;

	public RectCorners2D(Vector2D cornerA, Vector2D cornerB)
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
	public Module2D buildGeometry(CSG csg)
	{
		double width = Math.abs(cornerA.x()- cornerB.x());
		double height = Math.abs(cornerA.y() - cornerB.y());
		double x = 0.5*(cornerA.x()+cornerB.x());
		double y = 0.5*(cornerA.y()+cornerB.y());
		Module2DFrom2D translate = csg.csg2D().construct2D().translate2D(x, y);
		translate.add(csg.csg2D().shapes2D().rect2D(width, height));
		return translate;
	}
}
