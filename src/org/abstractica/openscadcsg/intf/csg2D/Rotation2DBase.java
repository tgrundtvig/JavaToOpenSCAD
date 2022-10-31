package org.abstractica.openscadcsg.intf.csg2D;

import org.abstractica.openscadcore.intf.Geometry2DFrom2D;
import org.abstractica.openscadcsg.intf.Angle;

public interface Rotation2DBase extends Translation2DBase
{
	//Rotations using angles
	default Geometry2DFrom2D rotate2D(Angle angle) {return rotate2D(angle.asDegrees());}

}
