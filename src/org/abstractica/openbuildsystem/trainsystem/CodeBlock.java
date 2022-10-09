package org.abstractica.openbuildsystem.trainsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.openbuildsystem.Adjust;
import org.abstractica.openbuildsystem.ClickSystem;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock
{
	private final double base = 7;
	private final double high = 14;
	private final double transitionLength = 15;
	private final double platformLength = 30;


	public Module2D highProfile(CSG csg, Adjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(platformLength+2*transitionLength,0));
		points.add(Vector2D.create(platformLength+2*transitionLength,base));
		points.add(Vector2D.create(platformLength+transitionLength,high));
		points.add(Vector2D.create(transitionLength,high));
		points.add(Vector2D.create(0,base));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module2D lowProfile(CSG csg, Adjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(platformLength+2*transitionLength,0));
		points.add(Vector2D.create(platformLength+2*transitionLength,5));
		points.add(Vector2D.create(0,5));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module3D get3D(Module2D profile, CSG csg, Adjust adj)
	{
		Module3D res = csg.csg3D().construct3D().linearExtrude(10, 4)
				.add(profile);
		res = csg.csg3D().construct3D().rotate3D(Angle.degrees(90), Angle.ZERO, Angle.degrees(90)).add(res);
		return res;
	}

	private Module2D curvedProfile(double height, CSG csg, Adjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(-5,0));
		points.add(Vector2D.create(5,0));
		points.add(Vector2D.create(5,height));
		points.add(Vector2D.create(-5,height));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module3D curveCodeBlock(CSG csg, Adjust adjust)
	{
		Module3D outside = curvedHigh(615, csg, adjust);
		outside = csg.csg3D().construct3D().translate3D(15, 0,0).add(outside);
		Module3D middle = curvedHigh(600, csg, adjust);
		Module3D inside = curvedLow(585, csg, adjust);
		inside = csg.csg3D().construct3D().translate3D(-15, 0,0).add(inside);
		Module3D base = curvedCodeBlockBase(csg, adjust);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(outside).add(middle).add(inside).add(base);
		return union;
	}

	private Module3D curvedLow(double radius, CSG csg, Adjust adjust)
	{
		Module2D profile = curvedProfile(base, csg, adjust);
		profile = csg.csg2D().construct2D().translate2D(radius, 0).add(profile);
		Module3D extrude = csg.csg3D().construct3D()
				.rotateExtrude(Angle.rotations(1.0/64.0),1024, 2).add(profile);
		return csg.csg3D().construct3D().translate3D(-radius, 0, 0).add(extrude);
	}

	public Module3D curvedHigh(double radius, CSG csg, Adjust adjust)
	{
		double transDist = high - base;
		int layers = (int) Math.round(transDist / 0.2);
		double d = ((1.0 / 64.0) / 4.0) / layers;
		Module3DFrom3D rampUp = csg.csg3D().construct3D().union3D();
		for(int i = 0; i < layers; ++i)
		{
			Module2D profile = curvedProfile(base + i * 0.2, csg, adjust);
			profile = csg.csg2D().construct2D().translate2D(radius, 0).add(profile);
			Module3D extrude = csg.csg3D().construct3D()
					.rotateExtrude(Angle.rotations(d),1024, 2).add(profile);
			rampUp.add(csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(d*i))
					.add(extrude));
		}
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(rampUp);

		//Ramp down
		Module3D rampDown = csg.csg3D().construct3D().mirror3D(Vector3D.create(0,1,0)).add(rampUp);
		rampDown = csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0/64.0)).add(rampDown);
		union.add(rampDown);

		//Platform
		Module2D platformProfile = curvedProfile(high, csg, adjust);
		platformProfile = csg.csg2D().construct2D().translate2D(radius, 0).add(platformProfile);
		Module3D platformExtrude = csg.csg3D().construct3D()
				.rotateExtrude(Angle.rotations((1.0/64.0)/2.0),1024, 2).add(platformProfile);
		platformExtrude = csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations((1.0/64.0)/4.0))
				.add(platformExtrude);
		union.add(platformExtrude);

		union = csg.csg3D().construct3D().translate3D(-radius, 0, 0).add(union);
		return union;
	}

	public Module3D testCodeBlock(CSG csg, Adjust adjust)
	{
		Module3D high = get3D(highProfile(csg, adjust), csg, adjust);
		Module3D low = get3D(lowProfile(csg, adjust), csg, adjust);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(straightCodeBlockBase(csg, adjust));
		union.add(csg.csg3D().construct3D().translate3D(-15,0,0)
				.add(high));
		union.add(high);
		union.add(csg.csg3D().construct3D().translate3D(15,0,0)
				.add(high));
		return union;
	}

	public Module3D straightCodeBlockBase(CSG csg, Adjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();

		//Mounts
		Module3D mount = codeBlockMount(csg, adjust);
		union.add(csg.csg3D().construct3D().translate3D(0,5,0).add(mount));
		union.add(csg.csg3D().construct3D().translate3D(0,60-5,0).add(mount));
		return union;
	}

	public Module3D curvedCodeBlockBase(CSG csg, Adjust adjust)
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();

		//Mounts
		Module3D mount = codeBlockMount(csg, adjust);
		Module3D mountBegin = csg.csg3D().construct3D().translate3D(600, 5,0).add(mount);
		union.add(mountBegin);
		Module3D mountEnd = csg.csg3D().construct3D().translate3D(600, -5,0).add(mount);
		mountEnd = csg.csg3D().construct3D().rotate3D(Angle.ZERO, Angle.ZERO, Angle.rotations(1.0/64)).add(mountEnd);
		union.add(mountEnd);
		union = csg.csg3D().construct3D().translate3D(-600, 0, 0).add(union);
		return union;
	}

	private Module3D codeBlockMount(CSG csg, Adjust adjust)
	{
		ClickSystem cs = new ClickSystem();
		Module3D box = csg.csg3D().shapes3D().box3D(60+2*adjust.getXYAdjust(), 10/*+2* adjust.getXYAdjust()*/, 5);
		box = csg.csg3D().construct3D().translate3D(0,0,2.5).add(box);
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(box);
		Module3D clickCutout = cs.clickerCutout(true, csg, adjust);
		diff.add(csg.csg3D().construct3D().translate3D(-30+5,0,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(30-5,0,0).add(clickCutout));
		return diff;
	}
}
