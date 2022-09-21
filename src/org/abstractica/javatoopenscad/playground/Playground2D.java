package org.abstractica.javatoopenscad.playground;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.modules.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGColor;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;

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
		G2D g2D = csg2D.g2D();
		G2DFrom2D g2D2D = csg2D.g2DFrom2D();


		// Generate your geometry here:
		return g2D2D.translate2D(1,1)
				.add(g2D2D.rotateAndProject2D(CSGMath.degrees(45), CSGMath.ANGLE_ZERO, CSGMath.ANGLE_ZERO)
						.add(g2D2D.color2D(CSGColor.color(1,0,0,1))
								.add(g2D.unitSquare2D())
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
