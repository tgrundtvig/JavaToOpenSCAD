package org.abstractica.openbuildsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class ClickSystem2
{
	private final double height = 10;
	private final double width = 0.4 * height;
	private final double clickerThickness = 0.1 * height;
	private final double clickerArmThickness = 0.20 * height;
	private final double clickSpace = 0.22*height;

	private final double clickerArmLength = 0.8*height;

	private final double clickerBaseThickness = 0.2*height;
	private final double clickerBaseLength = 0.8*height;

	public Module3D clicker(boolean reclick, CSG csg, Adjust adjust)
	{
		return csg.csg3D().construct3D().linearExtrude(width, 10)
				.add(clickerProfile(reclick, csg, adjust));
	}

	public Module3D clickerCutout(boolean reclick, CSG csg, Adjust adjust)
	{
		return csg.csg3D().construct3D()
				.translate3D(0,0,0.5*height)
				.add(csg.csg3D().construct3D().rotate3D(Angle.degrees(-90), Angle.ZERO, Angle.degrees(90))
				.add(csg.csg3D().construct3D().linearExtrude(width+2*adjust.getXYHoleAdjust(), 4)
				.add(clickerCutoutProfile(reclick, csg, adjust))));
	}

	private Module2D clickerProfile(boolean reclick, CSG csg, Adjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness,clickerBaseThickness));
		if(reclick)
		{
			points.add(Vector2D.create(0.5 * clickSpace + clickerArmThickness,
					clickerArmLength + clickerBaseThickness - 4 * clickerThickness));
		}
		else
		{
			points.add(Vector2D.create(0.5 * clickSpace + clickerArmThickness,
					clickerArmLength + clickerBaseThickness - 2 * clickerThickness));
		}
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness+clickerThickness*1.1,
				clickerArmLength + clickerBaseThickness - 2*clickerThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness-clickerThickness,
				clickerArmLength + clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace,
				clickerArmLength + clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace, clickerBaseThickness));
		points.add(Vector2D.create(0, clickerBaseThickness));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D().add(half);
		union.add(csg.csg2D().construct2D().mirror2D(1, 0).add(half));
		return csg.csg2D().construct2D().offsetRound2D(adjust.getXYAdjust(), 32).add(union);
	}

	private Module2D clickerCutoutProfile(boolean reclick, CSG csg, Adjust adjust)
	{
		List<Vector2D> points = new ArrayList<>();
		points.add(Vector2D.create(0,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,0));
		points.add(Vector2D.create(0.5*clickerBaseLength,clickerBaseThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness+0.1*clickerThickness,clickerBaseThickness));
		if(reclick)
		{
			points.add(Vector2D.create(0.5 * clickSpace + clickerArmThickness + 0.1 * clickerThickness,
					clickerArmLength + clickerBaseThickness - 4 * clickerThickness));
		}
		else
		{
			points.add(Vector2D.create(0.5 * clickSpace + clickerArmThickness + 0.1 * clickerThickness,
					clickerArmLength + clickerBaseThickness - 2 * clickerThickness));
		}
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness+clickerThickness,
				clickerArmLength + clickerBaseThickness - 2*clickerThickness));
		points.add(Vector2D.create(0.5*clickSpace+clickerArmThickness+0.1*clickerThickness,
				clickerArmLength + clickerBaseThickness));
		points.add(Vector2D.create(0,
				clickerArmLength + clickerBaseThickness));
		Module2D half = csg.csg2D().construct2D().polygon2D(points);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D().add(half);
		union.add(csg.csg2D().construct2D().mirror2D(1, 0).add(half));
		return csg.csg2D().construct2D().offsetRound2D(adjust.getXYHoleAdjust(), 32).add(union);
	}
}
