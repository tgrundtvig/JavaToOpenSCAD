package org.abstractica.javatoopenscad.playground;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.modules.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.plugininterfaces.Module3DImpl;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGColor;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.CSG3D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;

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
								.add(g2D.unitSquare2D())
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
