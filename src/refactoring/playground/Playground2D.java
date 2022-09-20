package refactoring.playground;

import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.OpenSCADModule;
import refactoring.coreimpl.core.Module2D;
import refactoring.coreimpl.core.ModuleFactory;
import refactoring.coreimpl.fileoutput.OpenSCADFileOutput;
import refactoring.modules.modulesimpl.CSGImpl;
import refactoring.modules.modulesimpl.plugininterfaces.Module2DImpl;
import refactoring.modules.modulesintf.CSG;
import refactoring.modules.modulesintf.CSGColor;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.csg2d.CSG2D;
import refactoring.modules.modulesintf.csg2d.g2d.G2D;
import refactoring.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;

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
								.add(g2D.UnitSquare2D())
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
