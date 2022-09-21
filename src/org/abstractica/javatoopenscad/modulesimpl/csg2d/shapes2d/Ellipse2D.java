package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

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
		Module2DFrom2D scale = csg.csg2D().construct2D().scale2D(diameterX, diameterY);
		return scale.add(csg.csg2D().shapes2D().unitCircle2D(angularResolution));
	}
}
