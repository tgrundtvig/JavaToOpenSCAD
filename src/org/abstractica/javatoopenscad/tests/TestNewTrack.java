package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Adjust;
import org.abstractica.openbuildsystem.AdjustImpl;
import org.abstractica.openbuildsystem.trainsystem.NewTrack;

import java.io.IOException;

public class TestNewTrack implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();
		Shapes2D s2D = csg2D.shapes2D();
		Construct2D c2D = csg2D.construct2D();
		Construct3D c3D = csg.csg3D().construct3D();

		Adjust adjust = new AdjustImpl(0.1, 0, -0.1, 0);
		NewTrack nt = new NewTrack();
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(c3D.translate3D(0,0,5).add(nt.innerCurvedTrack(600, csg, adjust)));
		union.add(c3D.translate3D(0,0,5).add(nt.outerCurvedTrack(600, csg, adjust)));
		Module3D sleeper = nt.sleeper(csg, adjust);
		union.add(sleeper);
		union.add(c3D.translate3D(-600,0,0)
				.add(c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0 / 64))
						.add(c3D.translate3D(600, 0, 0)
								.add(sleeper))));
		union.add(c3D.translate3D(-600,0,0)
				.add(c3D.rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0 / 32))
						.add(c3D.translate3D(600, 0, 0)
								.add(sleeper))));
		union.add(c3D.translate3D(0,0,10).add(nt.curvedCodeBlockBase(csg, adjust)));
		return union;
		//return nt.straightTrack(csg, adjust);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestNewTrack());
		OpenSCADFileOutput.generateOutput(module);
	}
}
