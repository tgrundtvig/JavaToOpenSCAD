package org.abstractica.javatoopenscad.scad.impl.impl3d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.scad3d.Coord3D;
import org.abstractica.javatoopenscad.scad.scad3d.Polar3D;
import org.abstractica.javatoopenscad.scad.scad3d.Vector3D;

public class Coord3DImpl implements Coord3D, Vector3D, Polar3D
{
	private final double x, y, z, r;
	private final Angle theta, elevation;

	public Coord3DImpl(double x, double y, double z, double r, Angle theta, Angle elevation)
	{
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

	@Override
	public Vector3D asVector3D()
	{
		return this;
	}

	@Override
	public Polar3D asPolar3D()
	{
		return this;
	}

	@Override
	public double r()
	{
		return r;
	}

	@Override
	public Angle theta()
	{
		return theta;
	}

	@Override
	public Angle elevation()
	{
		return elevation;
	}

	@Override
	public double x()
	{
		return x;
	}

	@Override
	public double y()
	{
		return y;
	}

	@Override
	public double z()
	{
		return z;
	}
}
