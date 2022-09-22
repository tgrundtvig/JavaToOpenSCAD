package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;


import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

public class Circle2D implements Module2DImpl
{
	private final double diameter;
	private final int angularResolution;

	public Circle2D(double diameter, int angularResolution)
	{
		this.diameter = diameter;
		this.angularResolution = angularResolution;
	}


	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameter", diameter);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		Module2DFrom2D scale = csg.csg2D().construct2D().scale2D(diameter, diameter);
		return scale.add(csg.csg2D().shapes2D().unitCircle2D(angularResolution));
	}
}
