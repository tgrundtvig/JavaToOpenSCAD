package org.abstractica.openbuildsystem.generators.clicksystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.math.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Shapes3D;
import org.abstractica.openbuildsystem.Adjust;
import org.abstractica.openbuildsystem.Print3DAdjust;

import java.util.ArrayList;
import java.util.List;

public class ClickSystemImpl implements ClickSystem
{
	private final CSG csg;
	private final Print3DAdjust adj;
	private final double unitHeight;
	private final double clickerWidth;
	private final double roundClickerWidth;
	private final double barbSize;
	private final double clickerArmThickness;
	private final double clickSpace;
	private final double clickerBaseThickness;
	private final double clickerBaseLength;
	private final double clickTension;
	private final double clickerPlayroom;

	public ClickSystemImpl(CSG csg, Print3DAdjust adj,
	                       double unitHeight,
	                       double clickerWidth,
	                       double roundClickerWidth,
	                       double barbSize,
	                       double clickerArmThickness,
	                       double clickSpace,
	                       double clickerBaseThickness,
	                       double clickerBaseLength,
	                       double clickTension,
	                       double clickerPlayroom)
	{
		this.csg = csg;
		this.adj = adj;
		this.unitHeight = unitHeight;
		this.clickerWidth = clickerWidth;
		this.roundClickerWidth = roundClickerWidth;
		this.barbSize = barbSize;
		this.clickerArmThickness = clickerArmThickness;
		this.clickSpace = clickSpace;
		this.clickerBaseThickness = clickerBaseThickness;
		this.clickerBaseLength = clickerBaseLength;
		this.clickTension = clickTension;
		this.clickerPlayroom = clickerPlayroom;
	}
	
	public ClickSystemImpl(CSG csg, Print3DAdjust adj, double unitHeight, double clickTension)
	{
		this.csg = csg;
		this.adj = adj;
		double h = 2*unitHeight;
		this.unitHeight = unitHeight;
		this.clickerWidth = 0.4 * h;
		this.roundClickerWidth = 0.5 * h;
		this.barbSize = 0.1 * h;
		this.clickerArmThickness = 0.2*h;
		this.clickSpace = 0.22 * h;
		this.clickerBaseThickness = 0.2 * h;
		this.clickerBaseLength = 0.8 * h;
		this.clickTension = clickTension;
		this.clickerPlayroom = 0.4;
	}

	public ClickSystemImpl(CSG csg, Print3DAdjust adj, double unitHeight)
	{
		this(csg, adj, unitHeight, 0.4);
	}
	
	@Override
	public Module3D clicker(double length)
	{
		return csg.csg3D().construct3D().linearExtrude(clickerWidth, 10)
				.add(clickerProfile(length));
	}

	@Override
	public Module3D clickerCutout(double length)
	{
		return csg.csg3D().construct3D()
				.translate3D(0,0,length)
				.add(csg.csg3D().construct3D().rotate3D(Angle.degrees(-90), Angle.ZERO, Angle.degrees(90))
				.add(csg.csg3D().construct3D()
						.linearExtrude(clickerWidth+2*adj.holeSquareTight().xy(), 4)
				.add(clickerCutoutProfile(length))));
	}

	@Override
	public Module3D roundClicker(double length)
	{
		if(length+0.0001 < 2*unitHeight)
		{
			throw new RuntimeException("Clicker length must be at least two times unitHeight!");
		}
		int ar = 1024;
		Adjust adjust = adj.solidRoundTight();
		double d0 = clickerBaseLength+2*adjust.xy();
		double d1 = clickSpace+2*clickerArmThickness+2*adjust.xy();
		double d2 = d1+2*barbSize;
		Construct3D c3d = csg.csg3D().construct3D();
		Shapes3D s3d = csg.csg3D().shapes3D();
		double h1 = 0.5*clickerBaseThickness+adjust.xy();
		Module3D c1 = s3d.cylinder3D(d0, h1, ar);
		c1 = c3d.translate3D(0, 0, 0.5*h1-adjust.xy()).add(c1);
		double h2 = 0.5*clickerBaseThickness;
		Module3D c2 = s3d.cone3D(d0, d1, h2, ar );
		c2 = c3d.translate3D(0,0, 0.5*h2+0.5*clickerBaseThickness).add(c2);
		double h3 = length - 2*clickerBaseThickness - clickTension;
		Module3D c3 = s3d.cylinder3D(d1, h3, ar);
		c3 = c3d.translate3D(0,0,-0.5*h3+length-clickerBaseThickness-clickTension).add(c3);
		double h4 = 0.5*clickerBaseThickness;
		Module3D c4 = s3d.cone3D(d1, d2, h4, ar);
		c4 = c3d.translate3D(0,0,-0.5*h4 + length - 0.5*clickerBaseThickness - clickTension).add(c4);
		double h5 = 0.5*clickerBaseThickness+clickTension+adjust.xy();
		Module3D c5 = s3d.cone3D(d2, d1, h5, ar);
		c5 = c3d.translate3D(0,0,-0.5*h5 + length+adjust.xy()).add(c5);
		Module3DFrom3D union = c3d.union3D().add(c1).add(c2).add(c3).add(c4).add(c5);
		Module3DFrom3D diff = c3d.difference3D().add(union);
		double hs = 1.5*unitHeight;
		Module3D space = s3d.box3D(clickSpace, clickerBaseLength+2, hs);
		space = c3d.translate3D(0,0,-0.5*hs + length).add(space);
		diff.add(space);
		Module3DFrom3D intersection = c3d.intersection3D().add(diff);
		double hi = length + 2;
		Module3D iBox = s3d.box3D(clickerBaseLength+2, roundClickerWidth, hi);
		iBox = c3d.translate3D(0,0,0.5*hi-1).add(iBox);
		intersection.add(iBox);
		intersection = c3d.rotateX(Angle.degrees(-90)).add(intersection);
		return intersection;
	}

	@Override
	public Module3D roundClickerCutout(double length)
	{
		return roundClickerCutout(length, adj.holeRoundTight().xy());
	}

	@Override
	public Module3D roundClickerCutoutVertical(double length)
	{
		return roundClickerCutout(length, adj.holeRoundTight().z());
	}

	private Module3D roundClickerCutout(double length, double adjust)
	{
		int ar = 1024;
		double d0 = clickerBaseLength+2*adjust;
		double d1 = clickSpace+2*clickerArmThickness+2*adjust;
		Construct3D c3d = csg.csg3D().construct3D();
		Shapes3D s3d = csg.csg3D().shapes3D();

		//Base 1
		double h1 = 0.5*clickerBaseThickness+clickerPlayroom;
		Module3D c1 = s3d.cylinder3D(d0, h1+0.001, ar);
		c1 = c3d.translate3D(0, 0, 0.5*h1-clickerPlayroom).add(c1);

		//Base 2
		double h2 = 0.5*clickerBaseThickness;
		Module3D c2 = s3d.cone3D(d0, d1, h2+0.001, ar );
		c2 = c3d.translate3D(0,0, 0.5*h2+0.5*clickerBaseThickness).add(c2);

		//Middle
		double h3 = 0.5*(length-2*clickerBaseThickness);
		Module3D c3 = s3d.cylinder3D(d1, h3+0.001, ar);
		c3 = c3d.translate3D(0,0,0.5*h3+clickerBaseThickness).add(c3);

		Module3DFrom3D half = c3d.union3D().add(c1).add(c2).add(c3);
		half = c3d.translate3D(0,0, -0.5*length).add(half);
		Module3DFrom3D union = c3d.union3D().add(half);
		union.add(c3d.mirror3D(0,0,1).add(half));
		union = c3d.translate3D(0,0,0.5*length).add(union);
		return union;
	}

	private Module2D clickerProfile(double length)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,0.5*clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness,clickerBaseThickness));

		points.add(Vector2D.create( 0.5*clickSpace+clickerArmThickness,
									length-clickerBaseThickness-clickTension));
		points.add(Vector2D.create( 0.5*clickSpace+clickerArmThickness+ barbSize,
									length-0.5*clickerBaseThickness-clickTension));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness, length));
		points.add(Vector2D.create(0.5*clickSpace, length));
		points.add(Vector2D.create(0.5*clickSpace, length-(1.5*unitHeight)));
		points.add(Vector2D.create(0, length-(1.5*unitHeight)));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D().add(half);
		union.add(csg.csg2D().construct2D().mirror2D(1, 0).add(half));
		return csg.csg2D().construct2D().offsetRound2D(adj.solidSquareTight().xy(),
				32).add(union);
	}

	private Module2D clickerCutoutProfile(double length)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,-clickerPlayroom));
		points.add(Vector2D.create(0.5*clickerBaseLength,-clickerPlayroom));
		points.add(Vector2D.create(0.5*clickerBaseLength,0.5*clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness,clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness,length-clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickerBaseLength,length-0.5*clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickerBaseLength,length+clickerPlayroom));
		points.add(Vector2D.create(0,length+clickerPlayroom));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D().add(half);
		union.add(csg.csg2D().construct2D().mirror2D(1, 0).add(half));
		return csg.csg2D().construct2D().offsetRound2D(adj.holeSquareTight().xy(), 32).add(union);
	}

}
