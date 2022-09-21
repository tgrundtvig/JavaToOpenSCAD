package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.modules;



import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;

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
		vertices.add(CSGMath.vector2D(-0.5, -0.5));
		vertices.add(CSGMath.vector2D(0.5, -0.5));
		vertices.add(CSGMath.vector2D(0.5, 0.5));
		vertices.add(CSGMath.vector2D(-0.5, 0.5));
		return csg.csg2D().g2D().polygon2D(vertices);
	}
}
