package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.javatoopenscad.csg.math.Vector3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d.Box3D;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d.Cone3D;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.shapes3d.HollowCylinder3D;

public class Shapes3DImpl extends AModuleFactory implements Shapes3D
{
	public Shapes3DImpl(CSGImpl csg)
	{
		super(csg);
	}

	@Override
	public Module3D cylinder3D(double diameter, double height, int angularResolution)
	{
		return module3D(new Cone3D(diameter, diameter, height, angularResolution));
	}

	@Override
	public Module3D hollowCylinder3D(double innerDiameter, double outerDiameter, double height, int angularResolution)
	{
		return module3D(new HollowCylinder3D(innerDiameter, outerDiameter, height, angularResolution));
	}

	@Override
	public Module3D cone3D(double diameterBottom, double diameterTop, double height, int angularResolution)
	{
		return module3D(new Cone3D(diameterBottom, diameterTop, height, angularResolution));
	}

	@Override
	public Module3D box3D(double sizeX, double sizeY, double sizeZ)
	{
		return module3D(new Box3D(sizeX, sizeY, sizeZ));
	}

	@Override
	public Module3D boxCorners3D(Vector3D cornerA, Vector3D cornerB)
	{
		Module3DFrom3D translate = getCSG().csg3D().construct3D().translate3D
		(
			0.5 *(cornerA.x() + cornerB.x()),
			0.5*(cornerA.y() + cornerB.y()),
			0.5*(cornerA.z() + cornerB.z())
		);
		return translate.add(box3D( Math.abs(cornerA.x() - cornerB.x()),
									Math.abs(cornerA.y() - cornerB.y()),
									Math.abs(cornerA.z() - cornerB.z())));
	}
}
