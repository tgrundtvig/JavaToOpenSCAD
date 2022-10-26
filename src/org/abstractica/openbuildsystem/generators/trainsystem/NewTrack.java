package org.abstractica.openbuildsystem.generators.trainsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.openbuildsystem.unused.ClickSystemOld;
import org.abstractica.openbuildsystem.Print3DAdjust;

import java.util.ArrayList;
import java.util.List;

public class NewTrack
{
	private final boolean reclick = true;
	private final double trackWidth = 6;
	private final double trackHeight = 10;

	public Module2D trackProfile(CSG csg, Print3DAdjust adjust)
	{
		/*
		return csg.csg2D().construct2D().union2D().add(csg.csg2D().shapes2D().rect2D(4, 12))
				.add(csg.csg2D().construct2D().translate2D(0,-2)
						.add(csg.csg2D().shapes2D().rect2D(12, 8)));
		*/
		return csg.csg2D().shapes2D().rect2D(trackWidth, trackHeight);
	}

	public Module2D toothProfile(CSG csg, Print3DAdjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(1, 0));
		points.add(Vector2D.create(0, 1));
		points.add(Vector2D.create(-1, 0));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module3D tooth(double width, CSG csg, Print3DAdjust adjust)
	{
		Module3D t = csg.csg3D().construct3D().linearExtrude(width, 2).add(toothProfile(csg, adjust));
		return csg.csg3D().construct3D().rotate3D(Angle.degrees(90), Angle.ZERO, Angle.degrees(90)).add(t);
	}

	public Module3D straightTrack(CSG csg, Print3DAdjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(straight(120, trackWidth, trackHeight, true, csg, adjust));

		//Add mounts
		//Start mount
		Module3D mount = trackMount(csg, adjust);
		Module3D mountStart = csg.csg3D().construct3D().translate3D(0, 5, 0).add(mount);
		union.add(mountStart);
		//Middle mount
		Module3DFrom3D mountMid = csg.csg3D().construct3D().union3D();
		mountMid.add(csg.csg3D().construct3D().translate3D(0, 60-5, 0).add(mount));
		mountMid.add(csg.csg3D().construct3D().translate3D(0, 60+5, 0).add(mount));
		union.add(mountMid);
		//End mount
		Module3D mountEnd = csg.csg3D().construct3D().translate3D(0, 120-5, 0).add(mount);
		union.add(mountEnd);
		return union;
	}

	public Module3D outerCurvedTrack(int radius, CSG csg, Print3DAdjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(curve(60, radius+40, Angle.rotations(1.0/32.0), trackWidth, trackHeight, csg, adjust));

		//Add mounts
		//Start mount
		Module3D mount = trackMount(csg, adjust);
		Module3D mountStart = csg.csg3D().construct3D().translate3D(radius+40, 5, 0).add(mount);
		union.add(mountStart);
		//Middle mount
		Module3DFrom3D mountMid = csg.csg3D().construct3D().union3D();
		mountMid.add(csg.csg3D().construct3D().translate3D(radius+40, -5, 0).add(mount));
		mountMid.add(csg.csg3D().construct3D().translate3D(radius+40, 5, 0).add(mount));
		mountMid = csg.csg3D().construct3D().rotateZ(Angle.rotations(1.0/64.0)).add(mountMid);
		union.add(mountMid);
		//End mount
		Module3D mountEnd = csg.csg3D().construct3D().translate3D(radius+40, -5, 0).add(mount);
		mountEnd = csg.csg3D().construct3D().rotateZ(Angle.rotations(1.0/32.0)).add(mountEnd);
		union.add(mountEnd);
		return csg.csg3D().construct3D().translate3D(-radius,0,0).add(union);
	}

	public Module3D innerCurvedTrack(double radius, CSG csg, Print3DAdjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(curve(0, radius-40, Angle.rotations(1.0/32.0), trackWidth, trackHeight, csg, adjust));
		//Add mounts
		//Start mount
		Module3D mount = trackMount(csg, adjust);
		Module3D mountStart = csg.csg3D().construct3D().translate3D(radius-40, 5, 0).add(mount);
		union.add(mountStart);
		//Middle mount
		Module3DFrom3D mountMid = csg.csg3D().construct3D().union3D();
		mountMid.add(csg.csg3D().construct3D().translate3D(radius-40, -5, 0).add(mount));
		mountMid.add(csg.csg3D().construct3D().translate3D(radius-40, 5, 0).add(mount));
		mountMid = csg.csg3D().construct3D().rotateZ(Angle.rotations(1.0/64.0)).add(mountMid);
		union.add(mountMid);
		//End mount
		Module3D mountEnd = csg.csg3D().construct3D().translate3D(radius-40, -5, 0).add(mount);
		mountEnd = csg.csg3D().construct3D().rotateZ(Angle.rotations(1.0/32.0)).add(mountEnd);
		union.add(mountEnd);
		return csg.csg3D().construct3D().translate3D(-radius,0,0).add(union);
	}

	public Module3D sleeper(CSG csg, Print3DAdjust adjust)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3DFrom3D sleeper = c3d.union3D();

		//Base sleeper
		Module3D base = csg.csg3D().shapes3D().box3D(120, 20, 5);
		base = c3d.translate3D(0,0,2.5).add(base);
		sleeper.add(base);

		//Code block mounts
		Module3D block = csg.csg3D().shapes3D().box3D(10+2*adjust.solidSquareTight().xy(), 20, 5);
		Module3D blockLeft = c3d.translate3D(-40+15, 0, 7.5).add(block);
		sleeper.add(blockLeft);
		Module3D blockRight = c3d.translate3D(40-15, 0, 7.5).add(block);
		sleeper.add(blockRight);


		ClickSystemOld cs = new ClickSystemOld();
		Module3D clickerCutoutTop = cs.clickerCutout(true, csg, adjust);
		Module3D clickerCutoutBottom = c3d.translate3D(0,0,5).add(clickerCutoutTop);
		Module3DFrom3D diff = c3d.difference3D().add(sleeper);
		//Left track mounts
		diff.add(c3d.translate3D(-40-6, 5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(-40-6, -5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(-40+6, 5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(-40+6, -5, 0).add(clickerCutoutBottom));

		//Right track mounts
		diff.add(c3d.translate3D(40+6, 5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(40+6, -5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(40-6, 5, 0).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(40-6, -5, 0).add(clickerCutoutBottom));

		//Code block mounts
		diff.add(c3d.translate3D(-40+15, 5, 5).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(-40+15, -5, 5).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(40-15, 5, 5).add(clickerCutoutBottom));
		diff.add(c3d.translate3D(40-15, -5, 5).add(clickerCutoutBottom));

		//Side mounts
		diff.add(c3d.translate3D(60-5, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(60-5, -5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-60+5, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-60+5, -5, 0).add(clickerCutoutTop));

		//Mid mounts
		diff.add(c3d.translate3D(15, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(15, -5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-15, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-15, -5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(5, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(5, -5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-5, 5, 0).add(clickerCutoutTop));
		diff.add(c3d.translate3D(-5, -5, 0).add(clickerCutoutTop));
		return diff;
	}

	private Module3D trackMount(CSG csg, Print3DAdjust adjust)
	{
		ClickSystemOld cs = new ClickSystemOld();
		Module3D box = csg.csg3D().shapes3D().box3D(20+2*adjust.solidSquareTight().xy(), 10, 5);
		box = csg.csg3D().construct3D().translate3D(0,0,2.5).add(box);
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(box);
		Module3D clickCutout = cs.clickerCutout(reclick, csg, adjust);
		diff.add(csg.csg3D().construct3D().translate3D(6,0,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(-6,0,0).add(clickCutout));
		return diff;
	}

	private Module2D codeBlockBaseProfile(CSG csg, Print3DAdjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(20,0));
		points.add(Vector2D.create(20,5));
		points.add(Vector2D.create(10,5));
		points.add(Vector2D.create(10,7));
		points.add(Vector2D.create(5,7));
		points.add(Vector2D.create(5,5));
		points.add(Vector2D.create(0,5));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D profile = csg.csg2D().construct2D().union2D().add(half);
		profile.add(csg.csg2D().construct2D().mirror2D(1,0).add(half));
		return profile;
	}

	public Module3D straightCodeBlockBase(CSG csg, Print3DAdjust adjust)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3D base = c3d.linearExtrude(120/*+2* adjust.getXYAdjust()*/, 4).add(codeBlockBaseProfile(csg, adjust));
		base = c3d.rotateX(Angle.degrees(90)).add(base);
		base = c3d.translate3D(0,60,0).add(base);
		Module3DFrom3D union = c3d.union3D().add(base);
		//Mounts
		Module3D mount = codeBlockMount(csg, adjust);
		union.add(c3d.translate3D(0,5,0).add(mount));
		union.add(c3d.translate3D(0,60,0).add(doubleCodeBlockMount(csg, adjust)));
		union.add(c3d.translate3D(0,120-5,0).add(mount));
		return union;
	}

	public Module3D curvedCodeBlockBase(CSG csg, Print3DAdjust adjust)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module2D extrudeProfile = csg.csg2D().construct2D().translate2D(600, 0).add(codeBlockBaseProfile(csg, adjust));
		Module3D base = c3d.rotateExtrude(Angle.rotations(1.0 / 32), 1024, 4).add(extrudeProfile);
		Module3DFrom3D union = c3d.union3D().add(base);
		//Mounts
		Module3D mount = codeBlockMount(csg, adjust);
		union.add(c3d.translate3D(600,5,0).add(mount));
		union.add(c3d.rotateZ(Angle.rotations(1.0 / 64))
				.add(c3d.translate3D(600,0,0).add(doubleCodeBlockMount(csg, adjust))));
		union.add(c3d.rotateZ(Angle.rotations(1.0 / 32))
				.add(c3d.translate3D(600,-5,0).add(mount)));
		//union.add(c3d.translate3D(0,60-5,0).add(codeBlockMount(csg, adjust)));
		return c3d.translate3D(-600,0,0).add(union);
	}

	private Module3D codeBlockMount(CSG csg, Print3DAdjust adjust)
	{
		ClickSystemOld cs = new ClickSystemOld();
		Module3D box = csg.csg3D().shapes3D().box3D(60+2*adjust.solidSquareTight().xy(), 10/*+2* adjust.getXYAdjust()*/, 5);
		box = csg.csg3D().construct3D().translate3D(0,0,2.5).add(box);
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(box);
		Module3D clickCutout = cs.clickerCutout(reclick, csg, adjust);
		diff.add(csg.csg3D().construct3D().translate3D(-30+5,0,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(30-5,0,0).add(clickCutout));
		return diff;
	}

	private Module3D doubleCodeBlockMount(CSG csg, Print3DAdjust adjust)
	{
		ClickSystemOld cs = new ClickSystemOld();
		Module3D box = csg.csg3D().shapes3D().box3D(60+2*adjust.solidSquareTight().xy(), 20/*+2* adjust.getXYAdjust()*/, 5);
		box = csg.csg3D().construct3D().translate3D(0,0,2.5).add(box);
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(box);
		Module3D clickCutout = cs.clickerCutout(reclick, csg, adjust);
		diff.add(csg.csg3D().construct3D().translate3D(-30+5,-5,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(30-5,-5,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(-30+5,5,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(30-5,5,0).add(clickCutout));
		return diff;
	}

	private Module3D straight(double length, double width, double height, boolean teeth, CSG csg, Print3DAdjust adjust)
	{
		Module2D profile = csg.csg2D().shapes2D().rect2D(width, height);
		Module3D track = csg.csg3D().construct3D()
				.linearExtrude(length, 2).add(profile);
		track = csg.csg3D().construct3D().rotateX(Angle.degrees(90)).add(track);
		track = csg.csg3D().construct3D().translate3D(0,0.5*length,0.5*height).add(track);
		if(!teeth) return track;
		//Create teeth
		int n = (int) Math.round(length / 2.0);
		double d = length / n;
		Module3D t = csg.csg3D().construct3D().translate3D(0, 0, height-0.01).add(tooth(width, csg, adjust));
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(track);
		for(int i = 0; i < n; ++i)
		{
			union.add(csg.csg3D().construct3D().translate3D(0, 0.5*d + i*d, 0).add(t));
		}
		return union;
	}

	private Module3D curve(int teeth, double radius, Angle angle, double width, double height, CSG csg, Print3DAdjust adjust)
	{
		Module2D profile = csg.csg2D().shapes2D().rect2D(width, height);
		Module2D tp = csg.csg2D().construct2D().translate2D(radius, 0.5*height).add(profile);
		Module3D track = csg.csg3D().construct3D().rotateExtrude(angle, 1024, 2).add(tp);
		if(teeth < 1) return track;
		//Create teeth
		int n = teeth;
		double d = angle.asRotations() / n;
		Module3D t = csg.csg3D().construct3D().translate3D(radius, 0, height-0.01).add(tooth(width, csg, adjust));
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(track);
		for(int i = 0; i < n; ++i)
		{
			Angle a = Angle.rotations(0.5*d + i*d);
			union.add(csg.csg3D().construct3D().rotateZ(a).add(t));
		}
		return union;
	}

	public Module3D switchLeft2(CSG csg, Print3DAdjust adjust)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		double midRadius = 510;
		double a = Math.asin(240.0/510.0);
		Angle angle = Angle.radians(a);
		int teeth = (int) Math.floor(0.5*a*(510+40));

		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		//Left straight
		Module3D ls = straight(480, trackWidth, trackHeight, true, csg, adjust);
		ls = c3d.translate3D(40, 0,5).add(ls);
		union.add(ls);
		//Right straight
		Module3D rs = straight(480, trackWidth, trackHeight, true, csg, adjust);
		rs = c3d.translate3D(-40, 0,5).add(rs);
		union.add(rs);
		//Left curve begin
		Module3D lcb = curve(0, 510-40, angle, trackWidth, trackHeight, csg, adjust);
		lcb = c3d.translate3D(-510, 0, 5).add(lcb);
		union.add(lcb);
		//Left curve end
		Module3D lce = curve(teeth, 510+40, angle, trackWidth, trackHeight, csg, adjust);
		lce = c3d.translate3D(-510, 0, 5).add(lce);
		lce = c3d.mirror3D(Vector3D.create(1,0,0)).add(lce);
		lce = c3d.translate3D(510, 0, 0).add(lce);
		lce = c3d.rotateZ(angle).add(lce);
		lce = c3d.translate3D(-510, 0, 0).add(lce);
		union.add(lce);

		//Right curve begin
		Module3D rcb = curve(teeth, 510+40, angle, trackWidth, trackHeight, csg, adjust);
		rcb = c3d.translate3D(-510, 0, 5).add(rcb);
		union.add(rcb);

		//Right curve end
		Module3D rce = curve(0, 510-40, angle, trackWidth, trackHeight, csg, adjust);
		rce = c3d.translate3D(-510, 0, 5).add(rce);
		rce = c3d.mirror3D(Vector3D.create(1,0,0)).add(rce);
		rce = c3d.translate3D(510, 0, 0).add(rce);
		rce = c3d.rotateZ(angle).add(rce);
		rce = c3d.translate3D(-510, 0, 0).add(rce);
		union.add(rce);

		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(union);
		//Right straight cut
		Module3D rsc = straight(360, 2.5, 15, false, csg, adjust);
		rsc = csg.csg3D().construct3D().translate3D(40-4.25+0.001, 0,4).add(rsc);
		diff.add(rsc);
		//Left straight cut
		Module3D lsc = straight(360, 5, 15, false, csg, adjust);
		lsc = csg.csg3D().construct3D().translate3D(-40+5.5-0.001, 0,4).add(lsc);
		diff.add(lsc);
		//Left curved cut
		Module3D lcc = curve(0, 510-40+4.25-0.001, angle, 2.5, 15, csg, adjust);
		lcc = csg.csg3D().construct3D().translate3D(-510, 0, 4).add(lcc);
		diff.add(lcc);
		//Right curved cut
		Module3D rcc = curve(0, 510-40+5.5-0.001, angle, 5, 15, csg, adjust);
		rcc = c3d.translate3D(-510, 0, 4).add(rcc);
		rcc = c3d.mirror3D(Vector3D.create(1,0,0)).add(rcc);
		rcc = c3d.translate3D(510, 0, 0).add(rcc);
		rcc = c3d.rotateZ(angle).add(rcc);
		rcc = c3d.translate3D(-510, 0, 0).add(rcc);
		diff.add(rcc);

		diff.add(csg.csg3D().shapes3D().box3D(150, 240, 50));
		return diff;
	}

	public Module3D switchLeft3(double totalLength, CSG csg, Print3DAdjust adjust)
	{
		Construct3D c3d = csg.csg3D().construct3D();
		double halfTotalLength = 0.5*totalLength;
		double midRadius = (60.0*60.0 + halfTotalLength*halfTotalLength)/120.0;
		System.out.println("Middle track radius: " + midRadius);
		double a = Math.asin(halfTotalLength/midRadius);
		Angle angle = Angle.radians(a);
		System.out.println("Angle: " + angle.asDegrees());
		int teeth = (int) Math.floor(0.5*a*(midRadius+40));

		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		//Left straight
		Module3D ls = straight(totalLength, trackWidth, trackHeight, true, csg, adjust);
		ls = c3d.translate3D(40, 0,5).add(ls);
		union.add(ls);
		//Right straight
		Module3D rs = straight(totalLength, trackWidth, trackHeight, true, csg, adjust);
		rs = c3d.translate3D(-40, 0,5).add(rs);
		union.add(rs);
		//Left curve begin
		Module3D lcb = curve(0, midRadius-40, angle, trackWidth, trackHeight, csg, adjust);
		lcb = c3d.translate3D(-midRadius, 0, 5).add(lcb);
		union.add(lcb);
		//Left curve end
		Module3D lce = curve(teeth, midRadius+40, angle, trackWidth, trackHeight, csg, adjust);
		lce = c3d.translate3D(-midRadius, 0, 5).add(lce);
		lce = c3d.mirror3D(Vector3D.create(1,0,0)).add(lce);
		lce = c3d.translate3D(midRadius, 0, 0).add(lce);
		lce = c3d.rotateZ(angle).add(lce);
		lce = c3d.translate3D(-midRadius, 0, 0).add(lce);
		union.add(lce);

		//Right curve begin
		Module3D rcb = curve(teeth, midRadius+40, angle, trackWidth, trackHeight, csg, adjust);
		rcb = c3d.translate3D(-midRadius, 0, 5).add(rcb);
		union.add(rcb);

		//Right curve end
		Module3D rce = curve(0, midRadius-40, angle, trackWidth, trackHeight, csg, adjust);
		rce = c3d.translate3D(-midRadius, 0, 5).add(rce);
		rce = c3d.mirror3D(Vector3D.create(1,0,0)).add(rce);
		rce = c3d.translate3D(midRadius, 0, 0).add(rce);
		rce = c3d.rotateZ(angle).add(rce);
		rce = c3d.translate3D(-midRadius, 0, 0).add(rce);
		union.add(rce);

		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(union);
		//Right straight cut
		Module3D rsc = straight(totalLength, 2.5, 15, false, csg, adjust);
		rsc = csg.csg3D().construct3D().translate3D(40-4.25+0.001, 0,4).add(rsc);
		diff.add(rsc);
		//Left straight cut
		Module3D lsc = straight(totalLength, 5, 15, false, csg, adjust);
		lsc = csg.csg3D().construct3D().translate3D(-40+5.5-0.001, 0,4).add(lsc);
		diff.add(lsc);
		//Left curved cut
		Module3D lcc = curve(0, midRadius-40+4.25-0.001, angle, 2.5, 15, csg, adjust);
		lcc = csg.csg3D().construct3D().translate3D(-midRadius, 0, 4).add(lcc);
		diff.add(lcc);
		//Right curved cut
		Module3D rcc = curve(0, midRadius-40+5.5-0.001, angle, 5, 15, csg, adjust);
		rcc = c3d.translate3D(-midRadius, 0, 4).add(rcc);
		rcc = c3d.mirror3D(Vector3D.create(1,0,0)).add(rcc);
		rcc = c3d.translate3D(midRadius, 0, 0).add(rcc);
		rcc = c3d.rotateZ(angle).add(rcc);
		rcc = c3d.translate3D(-midRadius, 0, 0).add(rcc);
		diff.add(rcc);

		//diff.add(csg.csg3D().shapes3D().box3D(150, 240, 50));
		return diff;
	}

	public Module3D switchLeft(CSG csg, Print3DAdjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		//Left straight
		Module3D ls = straight(480, trackWidth, trackHeight, true, csg, adjust);
		ls = csg.csg3D().construct3D().translate3D(40, 0,5).add(ls);
		union.add(ls);
		//Right straight
		Module3D rs = straight(480, trackWidth, trackHeight, true, csg, adjust);
		rs = csg.csg3D().construct3D().translate3D(-40, 0,5).add(rs);
		union.add(rs);
		//Left curved
		Module3D lc = curve(0, 560,Angle.rotations(4.0/32.0), trackWidth, trackHeight, csg, adjust);
		lc = csg.csg3D().construct3D().translate3D(-600, 0, 5).add(lc);
		union.add(lc);
		//Right curved
		Module3D rc = curve(180, 640,Angle.rotations(4.0/32.0), trackWidth, trackHeight, csg, adjust);
		rc = csg.csg3D().construct3D().translate3D(-600, 0, 5).add(rc);
		union.add(rc);
		//sleepers
		Module3D sleeper = csg.csg3D().shapes3D().box3D(120, 20, 5);
		//Module3D sleeper = sleeper(csg, adjust);
		sleeper = csg.csg3D().construct3D().translate3D(0,0,2.5).add(sleeper);

		//Combined sleepers
		for(int i = 0; i < 9; ++i)
		{
			union.add(switchCombinedSleeper(sleeper, sleeper, i*3, csg, adjust));
		}

		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(union);
		//Left straight cut
		Module3D lsc = straight(360, 5, 15, false, csg, adjust);
		lsc = csg.csg3D().construct3D().translate3D(40-5.5, 0,5).add(lsc);
		diff.add(lsc);
		//Right straight cut
		Module3D rsc = straight(360, 5, 15, false, csg, adjust);
		rsc = csg.csg3D().construct3D().translate3D(-40+5.5, 0,5).add(rsc);
		diff.add(rsc);
		//Left curved cut
		Module3D lcc = curve(0, 560+5.5,Angle.rotations(3.0/32.0), 5, 15, csg, adjust);
		lcc = csg.csg3D().construct3D().translate3D(-600, 0, 5).add(lcc);
		diff.add(lcc);
		//Right curved
		Module3D rcc = curve(180, 640-5.5,Angle.rotations(3.0/32.0), 5, 15, csg, adjust);
		rcc = csg.csg3D().construct3D().translate3D(-600, 0, 5).add(rcc);
		diff.add(rcc);
		return diff;
	}


	private Module3D switchCombinedSleeper(Module3D sleeperStraight, Module3D sleeperCurved, int i,
	                                       CSG csg, Print3DAdjust adjust)
	{
		Module3D sleeperCut = csg.csg3D().shapes3D().box3D(130, 20, 7);
		sleeperCut = csg.csg3D().construct3D().translate3D(0,0,3.5).add(sleeperCut);
		Module3D union = csg.csg3D().construct3D().union3D()
				.add(switchSleeperStraight(sleeperStraight, i, csg, adjust))
				.add(switchSleeperCurve(sleeperCurved, i, csg, adjust));
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(union);
		diff.add(switchSleeperStraight(sleeperCut, i+1, csg, adjust));
		diff.add(switchSleeperCurve(sleeperCut, i+1, csg, adjust));
		return diff;
	}

	private Module3D switchSleeperStraight(Module3D sleeper, int i, CSG csg, Print3DAdjust adjust)
	{
		return csg.csg3D().construct3D().translate3D(0,i*20, 0).add(sleeper);
	}

	private Module3D switchSleeperCurve(Module3D sleeper, int i, CSG csg, Print3DAdjust adjust)
	{
		Module3D s = csg.csg3D().construct3D().translate3D(600, 0,0).add(sleeper);
		Module3D sr = csg.csg3D().construct3D()
				.rotateZ(Angle.rotations(((3.0/32.0)/18)*i)).add(s);
		return csg.csg3D().construct3D().translate3D(-600, 0, 0).add(sr);
	}
}
