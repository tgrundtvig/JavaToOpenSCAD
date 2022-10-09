package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;

import java.io.IOException;

public class TestCone3D implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		return csg.csg3D().shapes3D()
				.cone3D(0, 20, 20, 32);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestCone3D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
