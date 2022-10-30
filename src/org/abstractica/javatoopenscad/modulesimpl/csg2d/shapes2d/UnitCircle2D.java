package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.math.Polar2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.math.Vector2D;

import java.util.ArrayList;

public class UnitCircle2D implements Module2DImpl
{
	private final int angularResolution;

	public UnitCircle2D(int angularResolution)
	{
		if(angularResolution < 3)
		{
			throw new RuntimeException("Angular resolution must be at least 3.");
		}
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		double segmentAngle = 1.0 / angularResolution;
		ArrayList<Vector2D> points = new ArrayList<>();
		for(int i = 0; i < angularResolution; ++i)
		{
			points.add(Polar2D.create(0.5, Angle.rotations(i*segmentAngle)).asVector2D());
		}
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
