package org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;

public class HollowCylinder3D implements Module3DImpl
{
	private final double innerDiameter;
	private final double outerDiameter;
	private final double height;
	private final int angularResolution;

	public HollowCylinder3D(double innerDiameter, double outerDiameter, double height, int angularResolution)
	{
		this.innerDiameter = innerDiameter;
		this.outerDiameter = outerDiameter;
		this.height = height;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("innerDiameter", innerDiameter);
		collector.add("outerDiameter", outerDiameter);
		collector.add("height", height);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		return csg.csg3D().construct3D().linearExtrude(height, 4)
				.add(csg.csg2D().shapes2D().hollowCircle2D(innerDiameter, outerDiameter, angularResolution));
	}
}
