package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;

import java.io.IOException;

public class TestResize3D implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(20, 10, 64);
		Module3DFrom3D resize = csg.csg3D().construct3D().resize3D(
				30, 10, 5, false, false, false);
		resize.add(cyl);
		return resize;
	}

	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestResize3D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
