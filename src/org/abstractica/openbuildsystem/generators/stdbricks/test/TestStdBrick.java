package org.abstractica.openbuildsystem.generators.stdbricks.test;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;
import org.abstractica.openbuildsystem.generators.stdbricks.StdBricks;

import java.io.IOException;

public class TestStdBrick implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Print3DAdjust adj = Print3DAdjustImpl.defaultAdjust;
		ClickSystem cs = new ClickSystemImpl(csg, adj, 5);
		StdBricks bricks = new StdBricks(csg, adj, cs);
		return bricks.stdBrick(2, 4);
	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestStdBrick());
		OpenSCADFileOutput.generateOutput(module);
	}
}
