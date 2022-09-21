package org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

public class Cylinder3D implements Module3DImpl
{
	private final double diameter;
	private final double height;
	private final int angularResolution;

	public Cylinder3D(double diameter, double height, int angularResolution)
	{
		this.diameter = diameter;
		this.height = height;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameter", diameter);
		collector.add("height", height);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		return csg.csg3D().construct3D().linearExtrude(height, 4)
						.add(csg.csg2D().shapes2D().circle2D(diameter, angularResolution));
	}
}
