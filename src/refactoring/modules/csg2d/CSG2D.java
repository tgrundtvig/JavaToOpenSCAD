package refactoring.modules.csg2d;

import refactoring.modules.csg2d.g2d.G2D;
import refactoring.modules.csg2d.g2dfrom2d.G2DFrom2D;
import refactoring.modules.csg2d.g2dfrom3d.G2DFrom3D;

public interface CSG2D
{
	//2D Shapes
	G2D g2D();
	//2D to 2D operations
	G2DFrom2D g2DFrom2D();
	//3D to 2D transformations
	G2DFrom3D g2DFrom3D();
}
