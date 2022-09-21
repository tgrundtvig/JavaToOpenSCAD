package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.*;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextSize;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;

import java.io.IOException;

public class TestText2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();

		TextFont font = CSGText.textFont("Consolas", "Regular");
		TextSize size = CSGText.textSize(25.4, 1);
		TextAlignment alignment = CSGText.textAlignment(
				TextHAlign.CENTER,
				TextVAlign.CENTER,
				TextDirection.LEFT_TO_RIGHT);
		return csg2D.text2D().text("Hello world!", font, size, alignment, 64);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new TestText2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
