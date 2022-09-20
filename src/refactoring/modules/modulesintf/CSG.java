package refactoring.modules.modulesintf;

import refactoring.modules.modulesintf.csg2d.CSG2D;
import refactoring.modules.modulesintf.csg3d.CSG3D;


public interface CSG
{
	CSG2D csg2D();
	CSG3D csg3D();
}
