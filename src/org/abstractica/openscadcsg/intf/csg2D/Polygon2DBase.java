package org.abstractica.openscadcsg.intf.csg2D;

import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcore.intf.polygon.Path;
import org.abstractica.openscadcore.intf.polygon.Vector2D;
import org.abstractica.openscadcsg.intf.AngleBase;

public interface Polygon2DBase extends Rotation2DBase
{
	default Geometry2D polygon2DGeometry(Iterable<Vector2D> vertices)
	{
		return polygon2DGeometry(polygon2D(vertices));
	}
	default Geometry2D polygon2DGeometry(Iterable<Vector2D> vertices, Iterable<Path> paths)
	{
		return polygon2DGeometry(polygon2D(vertices, paths));
	}
}
