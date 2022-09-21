package org.abstractica.javatoopenscad.modules.modulesintf;
import org.abstractica.javatoopenscad.modules.modulesintf.math.*;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AngleImpl;
import org.abstractica.javatoopenscad.coreimpl.core.impl.Coord2DImpl;
import org.abstractica.javatoopenscad.coreimpl.core.impl.Coord3DImpl;


public interface CSGMath
{
	//Angles
	Angle ANGLE_ZERO = new AngleImpl(0);
	static Angle rotations(double rotations)
	{
		if(rotations == 0.0)
		{
			return ANGLE_ZERO;
		}
		return new AngleImpl(rotations);
	}
	static Angle radians(double radians)
	{
		return rotations(radians / (Math.PI*2));
	}
	static Angle degrees(double degrees)
	{
		return rotations(degrees / 360.0);
	}

	static Angle add(Angle a, Angle b)
	{
		return rotations(a.asRotations() + b.asRotations());
	}
	static Angle subtract(Angle a, Angle b)
	{
		return rotations(a.asRotations() - b.asRotations());
	}

	static Angle fromTo(Angle from, Angle to)
	{
		return subtract(to, from);
	}

	static double sin(Angle angle)
	{
		return Math.sin(angle.asRadians());
	}

	static double cos(Angle angle)
	{
		return Math.cos(angle.asRadians());
	}

	static double tan(Angle angle)
	{
		return Math.tan(angle.asRadians());
	}

	static Angle atan(double a)
	{
		return radians(Math.atan(a));
	}

	static Angle atan2(double y, double x)
	{
		return radians(Math.atan2(y,x));
	}

	//Coordinates

	// Vector2D
	Vector2D VECTOR2D_ORIGO = new Coord2DImpl(0,0,0, ANGLE_ZERO);
	static Vector2D vector2D(double x, double y)
	{
			if(x == 0 && y == 0) return VECTOR2D_ORIGO;
			double r = Math.sqrt(x*x + y*y);
			Angle theta = radians(Math.atan2(y, x));
			return new Coord2DImpl(x, y, r, theta);
	}

	// Polar2D
	static Polar2D polar2D(double r, Angle theta)
	{
		if(r == 0)
		{
			return VECTOR2D_ORIGO.asPolar2D();
		}
		double x = r * Math.cos(theta.asRadians());
		double y = r * Math.sin(theta.asRadians());
		return vector2D(x, y).asPolar2D(); //Make sure Theta is in ]-0.5 : 0.5]
	}

	// Vector3D
	Vector3D VECTOR3D_ORIGO = new Coord3DImpl(0,0,0,0, ANGLE_ZERO, ANGLE_ZERO);
	static Vector3D vector3D(double x, double y, double z)
	{
		if(x == 0 && y == 0 && z == 0) return VECTOR3D_ORIGO;
		double r = Math.sqrt(x*x + y*y + z*z);
		if(r == 0.0) return VECTOR3D_ORIGO;
		if(x == 0 && y == 0)
		{
			Angle elevation = (z > 0) ? rotations(0.5) : rotations(-0.5);
			return new Coord3DImpl(x,y,z,r,ANGLE_ZERO,elevation);
		}
		Angle elevation = radians(Math.asin(z/r));
		Angle theta = radians(Math.atan2(y, x));
		return new Coord3DImpl(x,y,z,r, theta, elevation);
	}

	static Polar3D polar3D(double r, Angle theta, Angle elevation)
	{
		if(r == 0.0)
		{
			return VECTOR3D_ORIGO.asPolar3D();
		}
		else
		{
			double rot = elevation.asRotations();
			while(rot <= -0.5)
			{
				++rot;
			}
			while(rot > 0.5)
			{
				--rot;
			}
			elevation = rotations(rot);
			if(elevation.asRotations() > 0.25)
			{
				theta = add(theta, rotations(0.5));
				elevation = rotations(0.5 - elevation.asRotations());
			}
			else if(elevation.asRotations() < -0.25)
			{
				theta = add(theta, rotations(0.5));
				elevation = rotations(-0.5 - elevation.asRotations());
			}
		}
		double rProj = r / Math.cos(elevation.asRadians());
		double x = rProj * Math.cos(theta.asRadians());
		double y = rProj * Math.sin(theta.asRadians());
		double z = r * Math.sin(elevation.asRadians());
		return vector3D(x, y, z).asPolar3D();
	}
}
