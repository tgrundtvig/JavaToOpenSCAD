package org.abstractica.javatoopenscad.scad.scad3d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.impl.impl3d.Coord3DImpl;

public interface Coord3D
{
	static final Coord3D ORIGO = new Coord3DImpl(0,0,0,0, Angle.ZERO, Angle.ZERO);

	static Polar3D polar3D(double r, Angle theta, Angle elevation)
	{
		if(r == 0.0)
		{
			return ORIGO.asPolar3D();
		}
		else
		{
			if(elevation.asRotations() > 0.25)
			{
				theta = Angle.add(theta, Angle.rot(0.5));
				elevation = Angle.rot(0.5 - elevation.asRotations());
			}
			else if(elevation.asRotations() < -0.25)
			{
				theta = Angle.add(theta, Angle.rot(0.5));
				elevation = Angle.rot(-0.5 - elevation.asRotations());
			}
		}
		double rProj = r / Math.cos(elevation.asRadians());
		double x = rProj * Math.cos(theta.asRadians());
		double y = rProj * Math.sin(theta.asRadians());
		double z = r * Math.sin(elevation.asRadians());
		return new Coord3DImpl(x, y, z, r, theta, elevation);
	}

	static Vector3D vector3D(double x, double y, double z)
	{
		if(x == 0 && y == 0 && z == 0) return ORIGO.asVector3D();
		double r = Math.sqrt(x*x + y*y + z*z);
		if(x == 0 && y == 0)
		{
			Angle theta = Angle.ZERO;
			Angle elevation = (z > 0) ? Angle.rot(0.5) : Angle.rot(-0.5);
			return new Coord3DImpl(x,y,z,r,theta,elevation);
		}
		double rProj = Math.sqrt(x*x + y*y);
		Angle elevation = Angle.rad(Math.asin(z/r));
		Angle theta = Angle.rad(Math.atan2(y, x));
		/*
		Angle elevation = ??;
		return new Coord3DImpl(x, y, r, theta);
		*/

		return new Coord3DImpl(x,y,z,0.0, null, null);
	}

	Vector3D asVector3D();
	Polar3D asPolar3D();
}
