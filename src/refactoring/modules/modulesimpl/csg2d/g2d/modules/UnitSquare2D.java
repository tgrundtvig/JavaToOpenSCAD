package refactoring.modules.modulesimpl.csg2d.g2d.modules;



import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.coreimpl.core.Module2D;
import refactoring.modules.modulesimpl.plugininterfaces.Module2DImpl;
import refactoring.modules.modulesintf.CSG;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.math.Vector2D;

import java.util.ArrayList;
import java.util.List;


public class UnitSquare2D implements Module2DImpl
{
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
