package org.abstractica.openbuildsystem.generators.trainsystem.codeblocks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.openbuildsystem.unused.ClickSystemOld;
import org.abstractica.openbuildsystem.Print3DAdjust;

import java.util.ArrayList;
import java.util.List;

public class CodeBlocks
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private final double length = 60;
	private final double low = 5;
	private final double high = 14;
	private final double transitionLength = 27.5;
	private final double platformLength = 5;

	public CodeBlocks(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}


	public Module2D highProfile()
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(length,0));
		points.add(Vector2D.create(length,low));
		double lead = 0.5*(length - platformLength - 2*transitionLength);
		if(lead > 0.0001) //handle double precision...
		{
			points.add(Vector2D.create(length-lead, low));
		}
		points.add(Vector2D.create(0.5*length + 0.5*platformLength, high));
		points.add(Vector2D.create(0.5*length - 0.5*platformLength, high));
		if(lead > 0.0001) //handle double precision...
		{
			points.add(Vector2D.create(lead, low));
		}
		points.add(Vector2D.create(0,low));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module2D lowProfile()
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(length,0));
		points.add(Vector2D.create(length,low));
		points.add(Vector2D.create(0,low));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module3D get3D(Module2D profile)
	{
		Module3D res = csg.csg3D().construct3D().linearExtrude(10, 4)
				.add(profile);
		res = csg.csg3D().construct3D().rotate3D(Angle.degrees(90), Angle.ZERO, Angle.degrees(90)).add(res);
		return res;
	}

	private Module2D curvedProfile(double height)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(-5,0));
		points.add(Vector2D.create(5,0));
		points.add(Vector2D.create(5,height));
		points.add(Vector2D.create(-5,height));
		return csg.csg2D().construct2D().polygon2D(points);
	}

	public Module3D curveCodeBlock()
	{
		Module3D outside = curvedHigh(615);
		outside = csg.csg3D().construct3D().translate3D(15, 0,0).add(outside);
		Module3D middle = curvedHigh(600);
		Module3D inside = curvedLow(585);
		inside = csg.csg3D().construct3D().translate3D(-15, 0,0).add(inside);
		Module3D base = curvedCodeBlockBase();
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(outside).add(middle).add(inside).add(base);
		return union;
	}

	private Module3D curvedLow(double radius)
	{
		Module2D profile = curvedProfile(low);
		profile = csg.csg2D().construct2D().translate2D(radius, 0).add(profile);
		Module3D extrude = csg.csg3D().construct3D()
				.rotateExtrude(Angle.rotations(1.0/64.0),1024, 2).add(profile);
		return csg.csg3D().construct3D().translate3D(-radius, 0, 0).add(extrude);
	}

	public Module3D curvedHigh(double radius)
	{
		double transDist = high - low;
		int layers = (int) Math.round(transDist / adjust.layerHeight());
		double a = 1.0 / 64.0;
		double lead = 0.5*(length - platformLength - 2*transitionLength);
		Angle aLead = Angle.rotations(a * (lead / length));
		Angle aPlatform = Angle.rotations(a*(platformLength / length));
		double transitionAngle = a*(transitionLength / length);
		double dTransition = transitionAngle / layers;

		Module3DFrom3D union = csg.csg3D().construct3D().union3D();

		//Lead
		if(lead > 0.001)
		{
			Module2D leadProfile = curvedProfile(low);
			leadProfile = csg.csg2D().construct2D().translate2D(radius, 0).add(leadProfile);
			Module3D leadExtrude = csg.csg3D().construct3D()
					.rotateExtrude(aLead,1024, 2).add(leadProfile);
			union.add(leadExtrude);
			leadExtrude = csg.csg3D().construct3D().mirror3D(0,1,0).add(leadExtrude);
			leadExtrude = csg.csg3D().construct3D().rotateZ(Angle.rotations(a)).add(leadExtrude);
			union.add(leadExtrude);
		}

		//Create rampUp
		Module3DFrom3D rampUp = csg.csg3D().construct3D().union3D();
		for(int i = 0; i < layers; ++i)
		{
			Module2D profile = curvedProfile(low + i * 0.2);
			profile = csg.csg2D().construct2D().translate2D(radius, 0).add(profile);
			Module3D extrude = csg.csg3D().construct3D()
					.rotateExtrude(Angle.rotations(dTransition),1024, 2).add(profile);
			rampUp.add(csg.csg3D().construct3D().rotateZ(Angle.rotations(dTransition*i)).add(extrude));
		}
		if(lead > 0.001)
		{
			rampUp = csg.csg3D().construct3D().rotateZ(aLead).add(rampUp);
		}
		union.add(rampUp);

		//Ramp down
		Module3D rampDown = csg.csg3D().construct3D().mirror3D(Vector3D.create(0,1,0)).add(rampUp);
		rampDown = csg.csg3D().construct3D().rotateZ(Angle.rotations(a)).add(rampDown);
		union.add(rampDown);

		//Platform
		Module2D platformProfile = curvedProfile(high);
		platformProfile = csg.csg2D().construct2D().translate2D(radius, 0).add(platformProfile);
		Module3D platformExtrude = csg.csg3D().construct3D()
				.rotateExtrude(aPlatform,1024, 2).add(platformProfile);
		platformExtrude = csg.csg3D().construct3D().rotateZ(Angle.add(aLead, Angle.rotations(transitionAngle)))
							.add(platformExtrude);
		union.add(platformExtrude);

		union = csg.csg3D().construct3D().translate3D(-radius, 0, 0).add(union);
		return union;
	}

	public Module3D testCodeBlock()
	{
		Module3D high = get3D(highProfile());
		Module3D low = get3D(lowProfile());
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();
		union.add(straightCodeBlockBase());
		union.add(csg.csg3D().construct3D().translate3D(-15,0,0)
				.add(high));
		union.add(high);
		union.add(csg.csg3D().construct3D().translate3D(15,0,0)
				.add(high));
		return union;
	}

	public Module3D straightCodeBlockBase()
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();

		//Mounts
		Module3D mount = codeBlockMount();
		union.add(csg.csg3D().construct3D().translate3D(0,5,0).add(mount));
		union.add(csg.csg3D().construct3D().translate3D(0,60-5,0).add(mount));
		return union;
	}

	public Module3D curvedCodeBlockBase()
	{
		Module3DFrom3D union = csg.csg3D().construct3D().union3D();

		//Mounts
		Module3D mount = codeBlockMount();
		Module3D mountBegin = csg.csg3D().construct3D().translate3D(600, 5,0).add(mount);
		union.add(mountBegin);
		Module3D mountEnd = csg.csg3D().construct3D().translate3D(600, -5,0).add(mount);
		mountEnd = csg.csg3D().construct3D().rotateZ(Angle.rotations(1.0/64)).add(mountEnd);
		union.add(mountEnd);
		union = csg.csg3D().construct3D().translate3D(-600, 0, 0).add(union);
		return union;
	}

	private Module3D codeBlockMount()
	{
		ClickSystemOld cs = new ClickSystemOld();
		Module3D box = csg.csg3D().shapes3D().box3D(length+2*adjust.solidSquareTight().xy(),
													10/*+2* adjust.getXYAdjust()*/, 5);
		box = csg.csg3D().construct3D().translate3D(0,0,2.5).add(box);
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D().add(box);
		Module3D clickCutout = cs.clickerCutout(true, csg, adjust);
		diff.add(csg.csg3D().construct3D().translate3D(-0.5*length+5,0,0).add(clickCutout));
		diff.add(csg.csg3D().construct3D().translate3D(0.5*length-5,0,0).add(clickCutout));
		return diff;
	}
}
