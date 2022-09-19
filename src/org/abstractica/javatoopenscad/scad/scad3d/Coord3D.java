package org.abstractica.javatoopenscad.scad.scad3d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.module.Arguments;
import org.abstractica.javatoopenscad.scad.module.impl.ArgumentsImpl;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentsImplementation;

public class Coord3D implements Arguments, ArgumentsImplementation
{

	static final Coord3D ORIGO = new Coord3D(0,0,0,0, Angle.ZERO, Angle.ZERO);

	public static Coord3D polar3D(double r, Angle theta, Angle elevation)
	{
		if(r == 0.0)
		{
			return ORIGO;
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
		return new Coord3D(x, y, z, r, theta, elevation);
	}

	public static Coord3D vector3D(double x, double y, double z)
	{
		if(x == 0 && y == 0 && z == 0) return ORIGO;
		double r = Math.sqrt(x*x + y*y + z*z);
		if(x == 0 && y == 0)
		{
			Angle theta = Angle.ZERO;
			Angle elevation = (z > 0) ? Angle.rot(0.5) : Angle.rot(-0.5);
			return new Coord3D(x,y,z,r,theta,elevation);
		}
		double rProj = Math.sqrt(x*x + y*y);
		Angle elevation = Angle.rad(Math.asin(z/r));
		Angle theta = Angle.rad(Math.atan2(y, x));
		return new Coord3D(x,y,z,r, theta, elevation);
	}

	private final ArgumentsImpl argImpl;
	private final double x, y, z, r;
	private final Angle theta, elevation;

	public Coord3D(double x, double y, double z, double r, Angle theta, Angle elevation)
	{
		argImpl = new ArgumentsImpl(this);
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		if(r == 0.0)
		{
			theta = Angle.rot(0);
			elevation = Angle.rot(0);
		}
		else
		{
			if(elevation.asRotations() > 0.25)
			{
				theta = Angle.add(theta, Angle.rot(0.5));
				elevation = Angle.rot(0.5 - elevation().asRotations());
			}
			else if(elevation.asRotations() < -0.25)
			{
				theta = Angle.add(theta, Angle.rot(0.5));
				elevation = Angle.rot(-0.5 - elevation().asRotations());
			}
		}
		this.theta = theta;
		this.elevation = elevation;
	}

	public double r()
	{
		return r;
	}
	public Angle theta()
	{
		return theta;
	}
	public Angle elevation()
	{
		return elevation;
	}
	public double x()
	{
		return x;
	}
	public double y()
	{
		return y;
	}
	public double z()
	{
		return z;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
	}

	@Override
	public String getClearText()
	{
		return argImpl.getClearText();
	}

	@Override
	public int getUniqueId()
	{
		return argImpl.getUniqueId();
	}
}
