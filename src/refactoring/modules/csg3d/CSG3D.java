package refactoring.modules.csg3d;

import refactoring.modules.csg3d.g3d.G3D;
import refactoring.modules.csg3d.g3dfrom2d.G3DFrom2D;
import refactoring.modules.csg3d.g3dfrom3d.G3DFrom3D;

public interface CSG3D
{
	//3D shapes
	G3D g3d();

	//3D to 3D operations
	G3DFrom3D g3DFrom3D();

	//3D geometry from 2D shapes
	G3DFrom2D g3DFrom2D();
}
