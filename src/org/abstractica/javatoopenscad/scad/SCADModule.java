package org.abstractica.javatoopenscad.scad;

import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.scad2d.Vector2D;

public interface SCADModule extends Geometry
{
	String getUniqueId();
	String getClearText();
	Geometry getGeometry();

	interface ParameterCollector
	{
		void add(String name, int i);
		void add(String name, double d);
		void add(String name, String str);
		void add(String name, Vector2D vector2D);
		void add(String name, SCADModule module);
	}
}
