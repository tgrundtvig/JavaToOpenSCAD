package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.*;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.csg2d.Arrange2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

import java.io.IOException;

public class TestCircleOfModules2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		Shapes2D s2D = csg.csg2D().shapes2D();
		Arrange2D a2D = csg.csg2D().arrange2D();

		Module2D rect = s2D.rect2D(20, 10);
		Module2D circle = s2D.circle2D(20, 3);
		Module2DFrom2D circleOfModules =  a2D.circleOfModules2D(30, true);
		for(int i = 0; i < 4; ++i)
		{
			circleOfModules.add(rect);
			circleOfModules.add(circle);
			if(i == 2) rect.debugMark();
		}
		return circleOfModules;
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestCircleOfModules2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
