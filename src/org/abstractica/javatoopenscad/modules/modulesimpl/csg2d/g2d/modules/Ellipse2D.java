package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.modules;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;

public class Ellipse2D implements Module2DImpl
{
	private final int angularResolution;
	private final double diameterX, diameterY;

	public Ellipse2D(double diameterX, double diameterY, int angularResolution)
	{
		this.diameterX = diameterX;
		this.diameterY = diameterY;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameterX", diameterX);
		collector.add("diameterY", diameterY);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		Module2DFrom2D scale = csg.csg2D().g2DFrom2D().scale2D(diameterX, diameterY);
		return scale.add(csg.csg2D().g2D().unitCircle2D(angularResolution));
	}
}
