package org.abstractica.javatoopenscad.csg.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;

public interface Shapes3D
{
	Module3D cylinder3D(double diameter, double height, int angularResolution);
}
