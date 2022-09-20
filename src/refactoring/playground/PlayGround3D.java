package refactoring.playground;

import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.OpenSCADModule;
import refactoring.coreimpl.core.Module3D;
import refactoring.coreimpl.core.ModuleFactory;
import refactoring.coreimpl.fileoutput.OpenSCADFileOutput;
import refactoring.modules.modulesimpl.CSGImpl;
import refactoring.modules.modulesimpl.plugininterfaces.Module3DImpl;
import refactoring.modules.modulesintf.CSG;
import refactoring.modules.modulesintf.CSGColor;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.csg2d.CSG2D;
import refactoring.modules.modulesintf.csg2d.g2d.G2D;
import refactoring.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import refactoring.modules.modulesintf.csg3d.CSG3D;
import refactoring.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;

import java.io.IOException;

public class PlayGround3D implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();
		G2D g2D = csg2D.g2D();
		G2DFrom2D g2D2D = csg2D.g2DFrom2D();

		CSG3D csg3D = csg.csg3D();
		G3DFrom2D g3DFrom2D = csg3D.g3DFrom2D();


		// Generate your geometry here:
		return g3DFrom2D.linearExtrude(3, 2)
				.add(g2D2D.translate2D(1,1)
					.add(g2D2D.rotateAndProject2D(CSGMath.degrees(45), CSGMath.ANGLE_ZERO, CSGMath.ANGLE_ZERO)
						.add(g2D2D.color2D(CSGColor.color(1,0,0,1))
								.add(g2D.UnitSquare2D())
						)
					)
				);
	}

	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new PlayGround3D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
