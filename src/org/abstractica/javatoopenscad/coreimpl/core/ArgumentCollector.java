package org.abstractica.javatoopenscad.coreimpl.core;

import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Polar2D;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Polar3D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.polygon2d.Polygon2D;

import java.util.List;

public interface ArgumentCollector
{
	void add(String name, boolean b);
	void add(String name, int i);
	void add(String name, double d);
	void add(String name, String str);
	void add(String name, Angle angle);
	void add(String name, Vector2D v2D);
	void add(String name, Polar2D p2D);
	void add(String name, Vector3D v3D);
	void add(String name, Polar3D p3D);
	void add(String name, Polygon2D polygon);
	void add(String name, OpenSCADModule module);
	void add(String name, HasArguments element);
	void add(String name, List<HasArguments> elements);
}
