package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.modules.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.polygons.Path2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPolygon2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();
		G2D g2D = csg2D.g2D();
		G2DFrom2D g2D2D = csg2D.g2DFrom2D();


		List<Vector2D> vertices = new ArrayList<>();
		vertices.add(CSGMath.vector2D(-0.5, -0.5));
		vertices.add(CSGMath.vector2D(0.5, -0.5));
		vertices.add(CSGMath.vector2D(0.5, 0.5));
		vertices.add(CSGMath.vector2D(-0.5, 0.5));
		vertices.add(CSGMath.vector2D(-5, -5));
		vertices.add(CSGMath.vector2D(5, -5));
		vertices.add(CSGMath.vector2D(5, 5));
		vertices.add(CSGMath.vector2D(-5, 5));
		List<Integer> path1 = new ArrayList<>();
		path1.add(0);
		path1.add(1);
		path1.add(2);
		path1.add(3);
		List<Integer> path2 = new ArrayList<>();
		path2.add(4);
		path2.add(5);
		path2.add(6);
		path2.add(7);
		List<Path2D> paths = new ArrayList<>();
		paths.add(g2D.path2D(path1));
		paths.add(g2D.path2D(path2));
		return g2D.polygon2DMultiPaths(vertices, paths);
		//return g2D.polygon2DMultiPaths(vertices, paths);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestPolygon2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
