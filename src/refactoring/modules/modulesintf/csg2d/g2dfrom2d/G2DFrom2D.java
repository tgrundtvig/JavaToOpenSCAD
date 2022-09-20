package refactoring.modules.modulesintf.csg2d.g2dfrom2d;

import refactoring.coreimpl.core.Module2DFrom2D;
import refactoring.modules.modulesimpl.builtin.Color;
import refactoring.modules.modulesintf.math.Angle;


public interface G2DFrom2D
{
	Module2DFrom2D color2D(Color color);
	Module2DFrom2D translate2D(double x, double y);
	Module2DFrom2D rotate2D(Angle angle);
	Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z);
}
