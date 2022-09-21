package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.modules;


import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;

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
		Module2DFrom2D scale = csg.csg2D().g2DFrom2D().scale2D(diameter, diameter);
		return scale.add(csg.csg2D().g2D().unitCircle2D(angularResolution));
	}
}
