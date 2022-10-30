package org.abstractica.openbuildsystem.generators.trainsystem.rollingstock.test;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.trainsystem.rollingstock.TrainBogie;

import java.io.IOException;

public class TestTrainBogie implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Print3DAdjust adj = Print3DAdjustImpl.defaultAdjust;
		TrainBogie tb = new TrainBogie(csg, adj);
		//return tb.microSwitchCutout();
		//return tb.microSwitchHolderCutout();
		//return tb.microSwitchHolderDouble();
		//return tb.microSwitchCutout();
		//return tb.getMicroSwitchBogieBottomPrint();
		//return tb.getMicroSwitchBogieTopPrint();
		return tb.getDoubleMotorBogieBottomPrint();
		//return tb.getDoubleMotorBogieTopPrint();
		//return tb.mountPlate();
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestTrainBogie());
		OpenSCADFileOutput.generateOutput(module);
	}
}
