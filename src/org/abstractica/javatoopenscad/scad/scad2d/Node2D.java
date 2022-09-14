package org.abstractica.javatoopenscad.scad.scad2d;

import org.abstractica.javatoopenscad.scad.Node;

public interface Node2D extends Node, Geometry2D
{
	Node2D add(Geometry2D child);
}
