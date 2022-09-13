package org.abstractica.javatoopenscad.modules;

import org.abstractica.javatoopenscad.corescad.corescad3d.Geometry3D;

public interface Module3D extends Module, Geometry3D
{
	Geometry3D generateGeometry3D();
}
