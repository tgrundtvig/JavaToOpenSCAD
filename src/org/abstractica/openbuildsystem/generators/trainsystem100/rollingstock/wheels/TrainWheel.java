package org.abstractica.openbuildsystem.generators.trainsystem100.rollingstock.wheels;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.math.Polar2D;
import org.abstractica.javatoopenscad.csg.math.Vector2D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystemImpl;
import org.abstractica.openbuildsystem.generators.sourced.motors.dc.TTMotor;

import java.util.ArrayList;
import java.util.List;

public class TrainWheel
{
	//Parameters
	private final int teeth = 80;
	private final double toothHeight = 1;
	private final double toothWidth = 2;
	private final double baseWidth = 10;
	private final double flangeWidth = 5;
	private final double flangeBasewidth = 2;
	private final double flangeHeight = 5;
	private final double rimWidth = 5;
	private final double clickerYTranslate = 14;
	private final double bbDepth = 1.5;
	private final double bbRingDiameter = 12;
	private final double bbAxleDiameter = 8.1;
	private final int angularResolution = 1024;

	//Derived
	private final double baseDiameter = ((toothWidth * teeth) / Math.PI)+toothHeight;
	private final double connectorDiameter = baseDiameter - 2*rimWidth;

	//CSG and adjust
	private final CSG csg;
	private final Print3DAdjust adjust;
	private final Construct3D c3d;
	private final Shapes3D s3d;
	private final ClickSystem clickSystem;

	public TrainWheel(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
		this.c3d = csg.csg3D().construct3D();
		this.s3d = csg.csg3D().shapes3D();
		this.clickSystem = new ClickSystemImpl(csg, adjust, 5);
	}

	public Module3D trainWheel()
	{
		Module3DFrom3D res = c3d.difference3D().add(rimmedWheel());
		Module3D clickerCutout = clickSystem.clickerCutout(5);
		clickerCutout = c3d.translate3D(0,clickerYTranslate,0).add(clickerCutout);
		for(int i = 0; i < 8; ++i)
		{
			res.add(c3d.rotateZ(Angle.rotations(i * (1.0/8))).add(clickerCutout));
		}
		return res;
	}

	public Module3D wheelWithOutsideAxle()
	{
		Module3DFrom3D res = c3d.union3D();
		res.add(trainWheel());
		Module3D axle = c3d.translate3D(0,0,5).add(axle(true, 10, false));
		res.add(axle);
		return res;
	}

	public Module3D axle(boolean outside, double length, boolean motorAxleCutout)
	{
		Module3DFrom3D res = c3d.union3D();
		res.add(connectorPlate());
		double h = bbDepth;
		if(outside)
		{
			h+=5;
		}
		Module3D ringAxle = s3d.cylinder3D(bbRingDiameter, h, angularResolution);
		ringAxle = c3d.translate3D(0,0,0.5*h + 5).add(ringAxle);
		res.add(ringAxle);
		Module3D bbAxle = s3d.cylinder3D(bbAxleDiameter, length, angularResolution);
		bbAxle = c3d.translate3D(0,0,0.5*length + 5 + h).add(bbAxle);
		res.add(bbAxle);
		if(motorAxleCutout)
		{
			res = c3d.difference3D().add(res);
			TTMotor ttMotor = new TTMotor(true, csg, adjust);
			Module3D axleCutout = ttMotor.getAxleCutout();
			axleCutout = c3d.mirror3D(0,0,1).add(axleCutout);
			axleCutout = c3d.translate3D(0,0,h+5+length+0.001).add(axleCutout);
			res.add(axleCutout);
			return res;
		}
		return res;
	}
	public Module3D connectorPlate()
	{
		Module3DFrom3D res = c3d.difference3D();
		double d = connectorDiameter + 2*adjust.solidRoundTight().xy();
		Module3D base = s3d.cylinder3D(connectorDiameter, 5, angularResolution);
		base = c3d.translate3D(0,0,2.5).add(base);
		res.add(base);
		Module3D clickerCutout = clickSystem.clickerCutout(5);
		clickerCutout = c3d.translate3D(0,clickerYTranslate,0).add(clickerCutout);
		for(int i = 0; i < 4; ++i)
		{
			res.add(c3d.rotateZ(Angle.rotations(i * (1.0/4))).add(clickerCutout));
		}
		return res;
	}

	private Module3D rimmedWheel()
	{
		double d = connectorDiameter + 2*adjust.holeRoundTight().xy();
		double h = baseWidth + 1;
		Module3D cutout = s3d.cylinder3D(d, h, angularResolution);
		cutout = c3d.translate3D(0,0,0.5*h + flangeWidth).add(cutout);
		Module3D res = c3d.difference3D().add(flangedWheel()).add(cutout);
		return res;
	}

	private Module3D flangedWheel()
	{
		Module3DFrom3D res = c3d.union3D();
		res.add(flange());
		res.add(c3d.translate3D(0,0,flangeWidth).add(wheelBase()));
		return res;
	}

	private Module3D flange()
	{
		double flangeBaseDiameter = baseDiameter + 2*flangeHeight;
		System.out.println("Wheel flange diameter: " + flangeBaseDiameter);
		Module3D base = s3d.cylinder3D(flangeBaseDiameter, flangeBasewidth, angularResolution);
		base = c3d.translate3D(0,0,0.5*flangeBasewidth).add(base);
		double h = flangeWidth - flangeBasewidth;
		Module3D flange = s3d.cone3D(
				flangeBaseDiameter,
				baseDiameter,
				h,
				angularResolution);
		flange = c3d.translate3D(0,0,0.5*h + flangeBasewidth).add(flange);
		return c3d.union3D().add(base).add(flange);
	}

	private Module3D wheelBase()
	{
		Module3D res = c3d.linearExtrude(baseWidth,4).add(wheelProfile());
		res = c3d.translate3D(0,0,0.5*baseWidth).add(res);
		return res;
	}

	private Module2D wheelProfile()
	{
		double circumfence = toothWidth * teeth;
		double avgRadius = circumfence / (2*Math.PI);

		List<Vector2D> points = new ArrayList<>();
		double d = 1.0 / (2*teeth);
		for(int i = 0; i < 2*teeth; ++i)
		{
			Angle angle = Angle.rotations(i*d);
			double r = (i % 2 == 0) ? avgRadius + 0.5*toothHeight : avgRadius - 0.5*toothHeight;
			Polar2D p = Polar2D.create(r, angle);
			points.add(p.asVector2D());
		}
		return csg.csg2D().construct2D().polygon2D(points);
	}
}
