package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.Angle;

public class AngleImpl implements Angle
{
	private final double rotations;

	public AngleImpl(double rotations)
	{
		while(rotations <= -0.5)
		{
			++rotations;
		}
		while(rotations > 0.5)
		{
			--rotations;
		}
		this.rotations = rotations;
	}

	@Override
	public double asRadians()
	{
		return rotations*(Math.PI*2);
	}

	@Override
	public double asDegrees()
	{
		return rotations*360.0;
	}

	@Override
	public double asRotations()
	{
		return rotations;
	}
}
