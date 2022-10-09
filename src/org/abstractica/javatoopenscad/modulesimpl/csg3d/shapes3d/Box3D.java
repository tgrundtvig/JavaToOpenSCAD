package org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;

public class Box3D implements Module3DImpl
{
	private final double sizeX;
	private final double sizeY;
	private final double sizeZ;

	public Box3D(double sizeX, double sizeY, double sizeZ)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("sizeX", sizeX);
		collector.add("sizeÝ", sizeY);
		collector.add("sizeZ", sizeZ);
	}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Construct3D s3D = csg.csg3D().construct3D();
		Module2D rect = csg.csg2D().shapes2D().rect2D(sizeX, sizeY);
		return s3D.linearExtrude(sizeZ, 2).add(rect);
	}
}
