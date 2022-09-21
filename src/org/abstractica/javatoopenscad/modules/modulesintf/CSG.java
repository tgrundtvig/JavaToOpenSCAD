package org.abstractica.javatoopenscad.modules.modulesintf;

import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.CSG3D;


public interface CSG
{
	CSG2D csg2D();
	CSG3D csg3D();
}
