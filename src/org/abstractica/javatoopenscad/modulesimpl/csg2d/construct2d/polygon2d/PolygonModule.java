package org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.polygon2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Path2D;
import org.abstractica.javatoopenscad.csg.math.Vector2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

import java.util.List;

public class PolygonModule implements Module2DImpl
{
	private final Polygon2D polygon;
	private final Module2D polygonModule;

	public PolygonModule(Polygon2D polygon, Module2D polygonModule)
	{

		this.polygon = polygon;
		this.polygonModule = polygonModule;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		List<Vector2D> points = polygon.getVertices();
		for(int i = 0; i < points.size(); ++i)
		{
			Vector2D point = points.get(i);
			collector.add("point_"+i, point);
		}
		List<Path2D> paths = polygon.getPaths();
		if(paths != null)
		{
			for(int i = 0; i < paths.size(); ++i)
			{
				Path2D path = paths.get(i);
				collector.add("path_"+i, path);
			}
		}
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		return polygonModule;
	}
}
