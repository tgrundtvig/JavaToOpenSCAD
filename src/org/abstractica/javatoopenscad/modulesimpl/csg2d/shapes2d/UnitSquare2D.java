package org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d;



import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;

import java.util.ArrayList;
import java.util.List;


public class UnitSquare2D implements Module2DImpl
{
	public static final UnitSquare2D instance = new UnitSquare2D();

	private UnitSquare2D() {}

	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		List<Vector2D> vertices = new ArrayList<>();
		vertices.add(Vector2D.create(-0.5, -0.5));
		vertices.add(Vector2D.create(0.5, -0.5));
		vertices.add(Vector2D.create(0.5, 0.5));
		vertices.add(Vector2D.create(-0.5, 0.5));
		return csg.csg2D().construct2D().polygon2D(vertices);
	}
}
