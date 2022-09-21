package org.abstractica.javatoopenscad.modules.modulesintf.math;

public interface Polar3D
{
	double r();
	Angle theta();
	Angle elevation();
	Vector3D asVector3D();
}
