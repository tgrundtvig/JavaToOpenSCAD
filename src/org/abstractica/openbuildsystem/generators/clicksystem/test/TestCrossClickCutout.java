package org.abstractica.openbuildsystem.generators.clicksystem.test;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;

import java.io.IOException;

public class TestCrossClickCutout implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		ClickSystem cs = new ClickSystemImpl(csg, Print3DAdjustImpl.defaultAdjust, 5);
		Module3DFrom3D clickBox = csg.csg3D().construct3D().difference3D();
		clickBox.add(csg.csg3D().construct3D().translate3D(0,0,2.5)
				.add(csg.csg3D().shapes3D().box3D(10, 10, 5)));
		clickBox.add(cs.crossClickerCutout(5));
		/*
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(csg.csg3D().construct3D().translate3D(-10,0,0)
				.add(clickBox));
		union.add(csg.csg3D().construct3D().translate3D(10,0,0)
				.add(clickBox));
		union.add(csg.csg3D().construct3D().translate3D(0,-5,2.5)
				.add(cs.roundClicker(2, csg, adj)));
		return union;
		*/
		return clickBox;

	}


	// main method to execute playground code
	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new TestCrossClickCutout());
		OpenSCADFileOutput.generateOutput(module);
	}
}
