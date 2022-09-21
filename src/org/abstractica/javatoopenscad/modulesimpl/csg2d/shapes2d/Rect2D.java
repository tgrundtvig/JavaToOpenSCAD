package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;


import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

public class Rect2D implements Module2DImpl
{
	public final double width;
	public final double height;

	public Rect2D(double width, double height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("width", width);
		collector.add("height", height);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		Module2DFrom2D scale = csg.csg2D().construct2D().scale2D(width, height);
		return scale.add(csg.csg2D().shapes2D().unitSquare2D());
	}
}
