package org.abstractica.javatoopenscad.scad.impl.impl3d;

import org.abstractica.javatoopenscad.scad.Angle;
import org.abstractica.javatoopenscad.scad.Color;
import org.abstractica.javatoopenscad.scad.scad3d.*;

public class SCAD3DImpl implements SCAD3D
{
	@Override
	public Node3D translate3D(Coord3D vector)
	{
		return new Translate3D(vector);
	}

	@Override
	public Node3D rotate3D(Angle x, Angle y, Angle z)
	{
		return new Rotate3D(x, y, z);
	}

	@Override
	public Node3D scale3D(double xFactor, double yFactor, double zFactor)
	{
		return new Scale3D(xFactor, yFactor, zFactor);
	}

	@Override
	public Node3D mirror3D(Coord3D normal)
	{
		Vector3D norm = normal.asVector3D();
		return new Mirror3D(norm.x(), norm.y(), norm.z());
	}

	@Override
	public Node3D union3D()
	{
		return new Union3D();
	}

	@Override
	public Node3D intersection3D()
	{
		return new Intersection3D();
	}

	@Override
	public Node3D difference3D()
	{
		return new Difference3D();
	}

	@Override
	public Node3D hull3D()
	{
		return new Hull3D();
	}

	@Override
	public Node3D color3D(Color color)
	{
		return new Color3D(color);
	}
}
