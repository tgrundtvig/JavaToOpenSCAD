package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

import java.util.ArrayList;
import java.util.List;

public class IsoscelesTriangle implements Module2DImpl
{
	private final double base;
	private final double height;

	public IsoscelesTriangle(double base, double height)
	{
		this.base = base;
		this.height = height;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("base", base);
		collector.add("height", height);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0.5*base, 0));
		points.add(Vector2D.create(0, height));
		points.add(Vector2D.create(-0.5*base, 0));
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
