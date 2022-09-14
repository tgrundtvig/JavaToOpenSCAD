package org.abstractica.javatoopenscad.scad.scad3d;

import org.abstractica.javatoopenscad.scad.Node;

public interface Node3D extends Node, Geometry3D
{
		Node3D add(Geometry3D child);
}
