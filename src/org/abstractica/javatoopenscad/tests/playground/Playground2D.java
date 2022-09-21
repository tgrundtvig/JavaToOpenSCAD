package org.abstractica.javatoopenscad.tests.playground;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.CSGColor;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;

import java.io.IOException;

public class Playground2D implements Module2DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();
		Shapes2D s2D = csg.csg2D().shapes2D();
		Construct2D c2D = csg.csg2D().construct2D();


		// Generate your geometry here:
		return c2D.translate2D(1,1)
				.add(c2D.rotateAndProject2D(Angle.degrees(45), Angle.ZERO, Angle.ZERO)
						.add(csg2D.color2D().color2D(CSGColor.color(1,0,0,1))
								.add(s2D.unitSquare2D())
						)
				);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new Playground2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
