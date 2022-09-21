package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.modules.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;

import java.io.IOException;

public class TestLineOf2D implements Module2DImpl
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

		Module2D rect = g2D.rect2D(10, 20);
		return g2D.lineOfModule2D(rect, 4, 20);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestLineOf2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
