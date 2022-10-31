package org.abstractica.openscadcsg.intf.csg3d;

import org.abstractica.openscadcore.intf.Geometry3DFrom3D;
import org.abstractica.openscadcsg.intf.Angle;

public interface Rotation3DBase extends Translation3DBase
{
	default Geometry3DFrom3D rotate3D(Angle x, Angle y, Angle z)
	{
		return rotate3D(x.asDegrees(), y.asDegrees(), z.asDegrees());
	}
	default Geometry3DFrom3D rotate3DX(Angle x) {return rotate3D(x, zeroAngle(), zeroAngle());}
	default Geometry3DFrom3D rotate3DY(Angle y) {return rotate3D(zeroAngle(), y, zeroAngle());}
	default Geometry3DFrom3D rotate3DZ(Angle z) {return rotate3D(zeroAngle(), zeroAngle(), z);}
}
