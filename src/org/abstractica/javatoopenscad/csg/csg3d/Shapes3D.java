package org.abstractica.javatoopenscad.csg.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;

public interface Shapes3D
{
	Module3D cylinder3D(double diameter, double height, int angularResolution);
	Module3D hollowCylinder3D(double innerDiameter, double outerDiameter, double height, int angularResolution);
	Module3D cone3D(double diameterBottom, double diameterTop, double height, int angularResolution);
	Module3D box3D(double sizeX, double sizeY, double sizeZ);
	Module3D boxCorners3D(Vector3D cornerA, Vector3D cornerB);
}
