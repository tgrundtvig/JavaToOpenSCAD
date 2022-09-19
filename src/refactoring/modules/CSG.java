package refactoring.modules;

import refactoring.modules.csg2d.CSG2D;
import refactoring.modules.csg3d.CSG3D;


public interface CSG
{
	CSG2D csg2D();
	CSG3D csg3D();
}
