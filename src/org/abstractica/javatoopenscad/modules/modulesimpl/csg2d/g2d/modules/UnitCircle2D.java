package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.modules;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;

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
			points.add(CSGMath.polar2D(1, CSGMath.rotations(i*segmentAngle)).asVector2D());
		}
		return csg.csg2D().g2D().polygon2D(points);
	}
}
