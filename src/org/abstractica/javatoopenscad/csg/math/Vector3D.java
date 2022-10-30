package org.abstractica.javatoopenscad.csg.math;

import org.abstractica.javatoopenscad.csg.math.impl.Coord3DImpl;

public interface Vector3D
{
	Vector3D ORIGO = new Coord3DImpl(0,0,0,0, Angle.ZERO, Angle.ZERO);
	static Vector3D create(double x, double y, double z)
	{
		if(x == 0 && y == 0 && z == 0) return ORIGO;
		double r = Math.sqrt(x*x + y*y + z*z);
		if(r == 0.0) return ORIGO;
		if(x == 0 && y == 0)
		{
			Angle elevation = (z > 0) ? Angle.rotations(0.5) : Angle.rotations(-0.5);
			return new Coord3DImpl(x,y,z,r,Angle.ZERO,elevation);
		}
		Angle elevation = Angle.radians(Math.asin(z/r));
		Angle theta = Angle.radians(Math.atan2(y, x));
		return new Coord3DImpl(x,y,z,r, theta, elevation);
	}
	double x();
	double y();
	double z();
	Polar3D asPolar3D();
}
