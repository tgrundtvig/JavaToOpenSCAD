package org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d;

import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.Color;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Polar2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;


public interface G2DFrom2D
{
	Module2DFrom2D color2D(Color color);
	Module2DFrom2D translate2D(double x, double y);
	Module2DFrom2D translate2D(Vector2D t);
	Module2DFrom2D translate2D(Polar2D p);
	Module2DFrom2D rotate2D(Angle angle);
	Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z);
	Module2DFrom2D scale2D(double x, double y);
	Module2DFrom2D scale2D(Vector2D s);
	Module2DFrom2D mirror2D(double normX, double normY);
	Module2DFrom2D mirror2D(Vector2D norm);
	Module2DFrom2D mirror2D(Polar2D norm);
	Module2DFrom2D union2D();
	Module2DFrom2D intersection2D();
	Module2DFrom2D difference2D();
	Module2DFrom2D hull2D();
	Module2DFrom2D offset2D(double delta, boolean chamfer);
	Module2DFrom2D offsetRound2D(double radius, int angularResolution);
}
