package org.abstractica.javatoopenscad.modules.modulesintf.csg3d;

import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3d.G3D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom3d.G3DFrom3D;

public interface CSG3D
{
	//3D shapes
	G3D g3D();

	//3D to 3D operations
	G3DFrom3D g3DFrom3D();

	//3D geometry from 2D shapes
	G3DFrom2D g3DFrom2D();
}
