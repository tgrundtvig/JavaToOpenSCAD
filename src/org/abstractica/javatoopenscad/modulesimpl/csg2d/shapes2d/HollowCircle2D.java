package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

public class HollowCircle2D implements Module2DImpl
{
	private final double innerDiameter;
	private final double outerDiameter;
	private final int angularResolution;

	public HollowCircle2D(double innerDiameter, double outerDiameter, int angularResolution)
	{
		this.innerDiameter = innerDiameter;
		this.outerDiameter = outerDiameter;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("innerDiameter", innerDiameter);
		collector.add("outerDiameter", outerDiameter);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		CSG2D csg2d = csg.csg2D();
		Module2D innerCircle = csg2d.shapes2D().circle2D(innerDiameter, angularResolution);
		Module2D outerCircle = csg2d.shapes2D().circle2D(outerDiameter, angularResolution);
		return csg2d.construct2D().difference2D().add(outerCircle).add(innerCircle);
	}
}
