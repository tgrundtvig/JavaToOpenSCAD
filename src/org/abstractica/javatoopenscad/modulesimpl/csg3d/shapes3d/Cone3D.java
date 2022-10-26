package org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

public class Cone3D implements Module3DImpl
{
	private final double diameterBottom;
	private final double diameterTop;
	private final double height;
	private final int angularResolution;

	public Cone3D(double diameterBottom, double diameterTop, double height, int angularResolution)
	{
		this.diameterBottom = diameterBottom;
		this.diameterTop = diameterTop;
		this.height = height;
		this.angularResolution = angularResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("diameterBottom", diameterBottom);
		collector.add("diameterTop", diameterTop);
		collector.add("height", height);
		collector.add("angularResolution", angularResolution);
	}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Construct3D c3D = csg.csg3D().construct3D();
		Shapes2D s2D = csg.csg2D().shapes2D();
		if(diameterBottom < 0.00001)
		{
			double ratio = diameterBottom / diameterTop;
			//make it upside down
			Module3D cone = c3D.linearExtrude(height, ratio, 4)
					.add(s2D.circle2D(diameterTop, angularResolution));
			return c3D.rotateX(Angle.rotations(0.5)).add(cone);
		}
		else
		{
			double ratio = diameterTop / diameterBottom;
			return c3D.linearExtrude(height, ratio, 4)
					.add(s2D.circle2D(diameterBottom, angularResolution));
		}
	}
}
