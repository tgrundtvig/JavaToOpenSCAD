package org.abstractica.javatoopenscad.csg.csg3d;

import org.abstractica.javatoopenscad.csg.Angle;

public interface Polar3D
{
	Polar3D ORIGO = Vector3D.ORIGO.asPolar3D();
	static Polar3D polar3D(double r, Angle theta, Angle elevation)
	{
		if (r == 0.0)
		{
			return ORIGO;
		} else
		{
			double rot = elevation.asRotations();
			while (rot <= -0.5)
			{
				++rot;
			}
			while (rot > 0.5)
			{
				--rot;
			}
			elevation = Angle.rotations(rot);
			if (elevation.asRotations() > 0.25)
			{
				theta = Angle.add(theta, Angle.rotations(0.5));
				elevation = Angle.rotations(0.5 - elevation.asRotations());
			} else if (elevation.asRotations() < -0.25)
			{
				theta = Angle.add(theta, Angle.rotations(0.5));
				elevation = Angle.rotations(-0.5 - elevation.asRotations());
			}
		}
		double rProj = r / Math.cos(elevation.asRadians());
		double x = rProj * Math.cos(theta.asRadians());
		double y = rProj * Math.sin(theta.asRadians());
		double z = r * Math.sin(elevation.asRadians());
		return Vector3D.vector3D(x, y, z).asPolar3D();
	}
	double r();
	Angle theta();
	Angle elevation();
	Vector3D asVector3D();
}
