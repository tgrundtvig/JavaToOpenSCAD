package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.csg.csg2d.Path2D;

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
		Construct2D c2D = csg.csg2D().construct2D();
		//Get shortcuts to the api's you want to use:
		List<Vector2D> vertices = new ArrayList<>();
		vertices.add(Vector2D.create(-0.5, -0.5));
		vertices.add(Vector2D.create(0.5, -0.5));
		vertices.add(Vector2D.create(0.5, 0.5));
		vertices.add(Vector2D.create(-0.5, 0.5));
		vertices.add(Vector2D.create(-5, -5));
		vertices.add(Vector2D.create(5, -5));
		vertices.add(Vector2D.create(5, 5));
		vertices.add(Vector2D.create(-5, 5));
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
		paths.add(c2D.path2D(path1));
		paths.add(c2D.path2D(path2));
		return c2D.polygon2DMultiPaths(vertices, paths);
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
