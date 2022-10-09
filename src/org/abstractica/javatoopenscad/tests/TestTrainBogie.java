package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Adjust;
import org.abstractica.openbuildsystem.AdjustImpl;
import org.abstractica.openbuildsystem.trainsystem.TrainBogie;

import java.io.IOException;

public class TestTrainBogie implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Adjust adj = new AdjustImpl(0.1, 0, -0.1, 0);
		TrainBogie tb = new TrainBogie();
		//return tb.microSwitchCutout(csg, adj);
		//return tb.microSwitchHolderCutout(csg, adj);
		//return tb.microSwitchHolderDouble(csg, adj);
		//return tb.microSwitchCutout(csg, adj);
		//return tb.m
		//return tb.getMicroSwitchBogieBottomPrint(csg, adj);
		//return tb.getMicroSwitchBogieTopPrint(csg, adj);
		return tb.getDoubleMotorBogieBottomPrint(csg, adj);
		//return tb.getDoubleMotorBogieTopPrint(csg, adj);
		//return tb.mountPlate(csg, adj);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestTrainBogie());
		OpenSCADFileOutput.generateOutput(module);
	}
}
