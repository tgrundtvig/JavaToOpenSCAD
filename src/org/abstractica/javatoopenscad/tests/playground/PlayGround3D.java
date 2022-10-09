package org.abstractica.javatoopenscad.tests.playground;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.fileoutput.OpenSCADFileOutput;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Construct2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.openbuildsystem.*;
import org.abstractica.openbuildsystem.trainsystem.CodeBlock;
import org.abstractica.openbuildsystem.trainsystem.NewTrack;
import org.abstractica.openbuildsystem.trainsystem.TrainBogie;
import org.abstractica.openbuildsystem.trainsystem.TrainWheels;


import java.io.IOException;

public class PlayGround3D implements Module3DImpl
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

		Adjust adjust0 = new ZeroAdjust();
		Adjust adjust1 = new AdjustImpl(0.1, 0, -0.1, 0);


		//CodeBlock cb = new CodeBlock();
		//return cb.curveCodeBlock(csg, adjust1);
		//return cb.curvedHighCode(600, csg, adjust1);
		//return cb.straightCodeBlockBase(csg, adjust1);
		//return cb.get3D(cb.highProfile(csg, adjust1), csg, adjust1);
		//return cb.testTrack(csg, adjust1);
		/* Click System */

		/*
		ClickSystem cs = new ClickSystem();
		return cs.clicker(true, csg, adjust1);
		//return cs.clickerCutout(true, csg, adjust1);
		*/
		/*
		Module3D clickerCutout = cs.clickerCutout(csg, adjust1);
		Module3D box = csg.csg3D().construct3D().translate3D(0,0,2.5)
				.add(csg.csg3D().shapes3D().box3D(10, 10, 5));
		return csg.csg3D().construct3D().rotate3D(Angle.degrees(180), Angle.ZERO, Angle.ZERO)
				.add(csg.csg3D().construct3D().difference3D().add(box).add(clickerCutout));
		*/

/*
		Module3D clickerCutout = cs.clickerCutout(csg, adjust1);
		Module3D box = csg.csg3D().construct3D().translate3D(0,0,7.5)
				.add(csg.csg3D().shapes3D().box3D(10, 10, 5));
		return csg.csg3D().construct3D().difference3D().add(box).add(clickerCutout);

 */

		//NewTrack nt = new NewTrack();
		//return nt.swithcLeft(csg, adjust1);
		//return nt.sleeper(csg, adjust1);
		//return nt.curvedCodeBlockBase(csg, adjust1);

		/*Module3DFrom3D intersection = csg.csg3D().construct3D().intersection3D();
		intersection.add(nt.curvedTrack(csg, adjust1));
		intersection.add(csg.csg3D().shapes3D().box3D(100, 40, 50));
		return intersection;*/

		//TrainWheels tw = new TrainWheels();
		//return tw.sawWheel(csg, adjust1);
		//return tw.trainWheel(csg, adjust1);
		//return tw.longFreeAxle(csg, adjust1);
		//return tw.shortMotorAxle(csg, adjust1);
		//return tw.longMotorAxle(csg, adjust1);
		//TTMotor m = new TTMotor(true);
		//return m.getAxleCutout(csg, adjust1);
		//CrossAxles ca = new CrossAxles();
		//return ca.getAxleCutoutTest(12, 7, csg, adjust1);
		//PrintedTracks pt = new PrintedTracks();
		//return pt.curvedOuterTrack(csg, adjust1);
		//return pt.sleeperMid(csg, adjust1);
		//return pt.sleeperShared(csg, adjust1);
		//return pt.nutHole(csg, adjust1);
		//return pt.straightTrack(csg, adjust1);



		// Generate your geometry here:
		//MicroSwitchRoller ms = new MicroSwitchRoller();
		//return ms.microSwitchRollerCutout(10, 5, csg, adjust1);
		TrainBogie bogie = new TrainBogie();
		//return bogie.microSwitchHolderSingle(csg, adjust1);
		/*
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(csg.csg3D().construct3D().translate3D(-30,0,0)
				.add(csg.csg3D().construct3D().rotate3D(Angle.degrees(-90), Angle.degrees(90), Angle.degrees(0))
						.add(bogie.microSwitchHolderCutout(csg, adjust1))));
		union.add(csg.csg3D().construct3D().translate3D(30,0,0)
				.add(bogie.microSwitchHolderDouble(csg, adjust1)));
		return union;*/
		//return bogie.mountPlate(csg, adjust1);
		return bogie.mountLock(csg, adjust1);
		//return bogie.getMicroSwitchBogieBottomPrint(csg, adjust1);
		//return bogie.getDoubleMotorBogieBottomPrint(csg, adjust1);


	}

	public static void main(String[] args) throws IOException
	{
		ModuleFactory factory = new CSGImpl();
		OpenSCADModule module = factory.module3D(new PlayGround3D());
		OpenSCADFileOutput.generateOutput(module);
	}
}
