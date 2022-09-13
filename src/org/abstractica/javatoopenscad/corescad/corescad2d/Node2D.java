package org.abstractica.javatoopenscad.corescad.corescad2d;

public interface Node2D extends Geometry2D, Iterable<Geometry2D>
{
	Node2D add(Geometry2D child);
}
