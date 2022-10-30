package org.abstractica.openbuildsystem.generators.trainsystem.tracks.switches.tests;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.Print3DAdjustImpl;
import org.abstractica.openbuildsystem.generators.trainsystem.tracks.switches.ParallelSwitchTrackBuilder;
import org.abstractica.openbuildsystem.generators.trainsystem.tracks.TrackBuilder;

import java.io.IOException;

public class ParallelSwitchTrackBuilderTest implements Module3DImpl
{
	@Override
	public void getArguments(ArgumentCollector collector) {}

	@Override
	public Module3D buildGeometry(CSG csg)
	{
		//Get shortcuts to the api's you want to use:
		CSG2D csg2D = csg.csg2D();
		Construct3D c3D = csg.csg3D().construct3D();

		Print3DAdjust adj = Print3DAdjustImpl.defaultAdjust;

		TrackBuilder trackBuilder = new TrackBuilder(csg, adj, 5, 10, 2, 1);
		ParallelSwitchTrackBuilder sTrackBuilder = new ParallelSwitchTrackBuilder(
				csg, adj, trackBuilder, 80, 480, 120, true, 1024);
		Module3DFrom3D union = c3D.union3D();
		union.add(sTrackBuilder.trackSection(0,1,true, true));
		union.add(sTrackBuilder.trackSection(0,1,true, false));
		union.add(sTrackBuilder.trackSection(0,1, false, true));
		union.add(sTrackBuilder.trackSection(0,1, false, false));
		return union;
	}

	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		Module3D module = factory.module3D(new ParallelSwitchTrackBuilderTest());
		OpenSCADFileOutput.generateOutput(module);
	}
}
