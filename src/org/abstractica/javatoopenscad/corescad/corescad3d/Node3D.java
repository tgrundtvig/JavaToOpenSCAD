package org.abstractica.javatoopenscad.corescad.corescad3d;

public interface Node3D extends Geometry3D, Iterable<Geometry3D>
{
		Node3D add(Geometry3D child);
}
