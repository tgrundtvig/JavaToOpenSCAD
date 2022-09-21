package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.Angle;

public interface Construct2D
{
	//Polygons
	Path2D path2D(Iterable<Integer> path);
	Module2D polygon2D(Iterable<Vector2D> vertices);
	Module2D polygon2DMultiPaths(Iterable<Vector2D> points, Iterable<Path2D> paths);
	Module2D polygon2D(Iterable<Vector2D> points, Iterable<Integer> path);

	//Translate
	Module2DFrom2D translate2D(double x, double y);
	Module2DFrom2D translate2D(Vector2D t);
	Module2DFrom2D translate2D(Polar2D p);

	//Rotate
	Module2DFrom2D rotate2D(Angle angle);
	Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z);

	//Scale
	Module2DFrom2D scale2D(double x, double y);
	Module2DFrom2D scale2D(Vector2D s);

	//Mirror
	Module2DFrom2D mirror2D(double normX, double normY);
	Module2DFrom2D mirror2D(Vector2D norm);
	Module2DFrom2D mirror2D(Polar2D norm);

	//Boolean operations
	Module2DFrom2D union2D();
	Module2DFrom2D intersection2D();
	Module2DFrom2D difference2D();

	//change size
	//Scale
	//Resize

	//Hull
	Module2DFrom2D hull2D();

	//ToDo minkowski
	//ToDo project

	//Offset
	Module2DFrom2D offset2D(double delta, boolean chamfer);
	Module2DFrom2D offsetRound2D(double radius, int angularResolution);
}
