package org.abstractica.javatoopenscad.corescad.impl.impl3d;

import org.abstractica.javatoopenscad.corescad.Angle;
import org.abstractica.javatoopenscad.corescad.Color;
import org.abstractica.javatoopenscad.corescad.corescad3d.*;

public class CoreSCAD3DImpl implements CoreSCAD3D
{
	@Override
	public Module3D module3D(String name, Geometry3D geometry3D)
	{
		return new Module3D(name, geometry3D);
	}

	@Override
	public Geometry3D sphere3D(double diameter, int angularResolution)
	{
		return new Sphere3D(diameter, angularResolution);
	}

	@Override
	public Geometry3D cylinder3D(double diameter, double height, int angularResolution)
	{
		return new Cylinder3D(diameter, height, angularResolution);
	}

	@Override
	public Geometry3D cone3D(double bottomDiameter, double topDiameter, double height, int angularResolution)
	{
		return new Cone3D(bottomDiameter, topDiameter, height, angularResolution);
	}

	@Override
	public Geometry3D box3D(double sizeX, double sizeY, double sizeZ)
	{
		return new Box3D(sizeX, sizeY, sizeZ);
	}

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
