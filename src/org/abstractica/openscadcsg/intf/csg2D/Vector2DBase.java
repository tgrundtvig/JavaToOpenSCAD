package org.abstractica.openscadcsg.intf.csg2D;

import org.abstractica.openscadcore.intf.polygon.Vector2D;
import org.abstractica.openscadcsg.intf.Angle;
import org.abstractica.openscadcsg.intf.AngleBase;

public interface Vector2DBase extends AngleBase
{
	//Vector2D
	default double length(Vector2D v)
	{
		return Math.sqrt(v.x()*v.x() + v.y()*v.y());
	}
	default Angle angle(Vector2D v)
	{
		double l = length(v);
		return atan2(v.y()/l, v.x()/l);
	}
	default Vector2D normalized(Vector2D v)
	{
		double l = length(v);
		return vector2D(v.x()/l, v.y()/l);
	}
	default Vector2D add(Vector2D a, Vector2D b)
	{
		return vector2D(a.x()+b.x(), a.y()+b.y());
	}
	default Vector2D sub(Vector2D a, Vector2D b)
	{
		return vector2D(a.x()-b.x(), a.y()-b.y());
	}
	default Vector2D mul(Vector2D v, double d)
	{
		return vector2D(v.x()*d, v.y()*d);
	}
	default Vector2D mul(double d, Vector2D v)
	{
		return mul(v, d);
	}
	default Vector2D fromTo(Vector2D from, Vector2D to)
	{
		return sub(to, from);
	}
}
