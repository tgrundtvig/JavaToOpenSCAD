package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.CSGText;
import org.abstractica.javatoopenscad.csg.csg2d.Arrange2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

import java.io.IOException;

public class TestAddChars2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		Shapes2D s2D = csg.csg2D().shapes2D();
		Arrange2D a2D = csg.csg2D().arrange2D();
		Module2DFrom2D circleOfModules =  a2D.circleOfModules2D(30, false);
		return csg.csg2D().text2D().addChars(
				circleOfModules, "Hello world!", CSGText.defaultAttributes, 16);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestAddChars2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
