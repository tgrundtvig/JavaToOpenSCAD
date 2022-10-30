package org.abstractica.openbuildsystem.generators.sourced.motors.dc.test;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.sourced.motors.dc.TTMotor;

import java.io.IOException;

public class TTMotorTest implements Module3DImpl
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


		// Generate your geometry here:
		TTMotor motor = new TTMotor(false, csg, Print3DAdjustImpl.defaultAdjust);
		return motor.getCutout();
	}

	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TTMotorTest());
		OpenSCADFileOutput.generateOutput(module);
	}
}
