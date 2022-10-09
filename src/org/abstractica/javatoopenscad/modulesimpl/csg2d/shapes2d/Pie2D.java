package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Polar2D;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

import java.util.ArrayList;
import java.util.List;

public class Pie2D implements Module2DImpl
{
	private final double diameter;
	private final double start;
	private final double end;
	private final int steps;

	public Pie2D(double diameter, Angle start, Angle end, int steps)
	{
		this.diameter = diameter;
		this.start = start.asRotations();
		this.end = end.asRotations();
		this.steps = steps;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameter", diameter);
		collector.add("start", start);
		collector.add("end", end);
		collector.add("steps", steps);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		double stepSize = start < end ?
				(end-start)/steps :
				(1.0 - (start-end))/steps;
		double r = 0.5*diameter;
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.ORIGO);
		for(int i = 0; i <= steps; ++i)
		{
			points.add(Polar2D.create(r, Angle.rotations(start + i*stepSize)).asVector2D());
		}
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
