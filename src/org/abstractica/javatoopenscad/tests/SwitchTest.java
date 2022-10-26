package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.trainsystem.tracks.Switch;

import java.io.IOException;

public class SwitchTest implements Module3DImpl
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

		Print3DAdjust adj = Print3DAdjustImpl.defaultAdjust;

		Switch sw = new Switch(csg, adj);
		Module3DFrom3D union = c3D.union3D();

		//union.add(sw.switchLayout());
		//union.add(sw.crossSection());
		//union.add(sw.switcher());

		union.add(sw.leftCurve02());
		union.add(sw.rightStraight02());
		union.add(sw.doubleSleeper(0));
		union.add(sw.doubleSleeper(1));
		union.add(sw.doubleSleeper(2));
		union.add(sw.curveSwitchTrack());
		union.add(sw.straightSwitchTrack());

		//union.add(sw.crossSection());
		//union.add(sw.allSleepers());
		return union;
	}

	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		Module3D module = factory.module3D(new SwitchTest());
		OpenSCADFileOutput.generateOutput(module);
	}
}
