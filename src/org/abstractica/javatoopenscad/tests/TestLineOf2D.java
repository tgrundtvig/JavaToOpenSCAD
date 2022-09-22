package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.csg2d.Arrange2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

import java.io.IOException;

public class TestLineOf2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		Shapes2D s2D = csg.csg2D().shapes2D();
		Arrange2D a2D = csg.csg2D().arrange2D();

		Module2D rect = s2D.rect2D(10, 20);
		return a2D.lineOfModule2D(rect, 4, 20);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestLineOf2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
