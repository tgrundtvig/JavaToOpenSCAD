package org.abstractica.javatoopenscad.tests.playground;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.openbuildsystem.*;
import org.abstractica.openbuildsystem.generators.trainsystem.rollingstock.TrainWheels;

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

		Print3DAdjust adj = Print3DAdjustImpl.defaultAdjust;

		// Generate your geometry here:

		TrainWheels tw = new TrainWheels(csg, adj);
		return tw.sawWheelProfile(1,1, 64);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module2D(new Playground2D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
