package org.abstractica.javatoopenscad.csg;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg3d.CSG3D;

public interface CSG
{
	CSG2D csg2D();
	CSG3D csg3D();
}
