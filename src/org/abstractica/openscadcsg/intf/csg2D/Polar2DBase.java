package org.abstractica.openscadcsg.intf.csg2D;

import org.abstractica.openscadcore.intf.polygon.Vector2D;
import org.abstractica.openscadcsg.intf.Angle;

public interface Polar2DBase extends Vector2DBase
{
	//Polar2D
	Polar2D polar2D(double r, Angle theta);
	default Polar2D asPolar2D(Vector2D v)
	{
		double r = length(v);
		return polar2D(r, atan2(v.y()/r, v.x()/r));
	}
	default Vector2D asVector2D(Polar2D p)
	{
		return vector2D(cos(p.theta())*p.r(), sin(p.theta())*p.r());
	}
}
