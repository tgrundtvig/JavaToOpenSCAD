package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2dfrom2d;


import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.*;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Polar2D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;

public class G2DFrom2DImpl extends AModuleFactory implements G2DFrom2D
{
	public G2DFrom2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module2DFrom2D color2D(Color color)
	{
		return module2DFrom2D(color);
	}

	@Override
	public Module2DFrom2D translate2D(double x, double y)
	{
		return module2DFrom2D(new Translate2D(x, y));
	}

	@Override
	public Module2DFrom2D translate2D(Vector2D t)
	{
		return translate2D(t.x(), t.y());
	}

	@Override
	public Module2DFrom2D translate2D(Polar2D p)
	{
		return translate2D(p.asVector2D());
	}

	@Override
	public Module2DFrom2D rotate2D(Angle angle)
	{
		return module2DFrom2D(new Rotate(CSGMath.ANGLE_ZERO, CSGMath.ANGLE_ZERO, angle));
	}

	@Override
	public Module2DFrom2D rotateAndProject2D(Angle x, Angle y, Angle z)
	{
		return module2DFrom2D(new Rotate(x, y, z));
	}

	@Override
	public Module2DFrom2D scale2D(double x, double y)
	{
		return module2DFrom2D(new Scale2D(x, y));
	}

	@Override
	public Module2DFrom2D scale2D(Vector2D s)
	{
		return scale2D(s.x(), s.y());
	}

	@Override
	public Module2DFrom2D mirror2D(double normX, double normY)
	{
		return module2DFrom2D(new Mirror2D(normX, normY));
	}

	@Override
	public Module2DFrom2D mirror2D(Vector2D norm)
	{
		return mirror2D(norm.x(), norm.y());
	}

	@Override
	public Module2DFrom2D mirror2D(Polar2D norm)
	{
		return mirror2D(norm.asVector2D());
	}

	@Override
	public Module2DFrom2D union2D()
	{
		return module2DFrom2D(Union.instance);
	}

	@Override
	public Module2DFrom2D intersection2D()
	{
		return module2DFrom2D(Intersection.instance);
	}

	@Override
	public Module2DFrom2D difference2D()
	{
		return module2DFrom2D(Difference.instance);
	}

	@Override
	public Module2DFrom2D hull2D()
	{
		return module2DFrom2D(Hull.instance);
	}

	@Override
	public Module2DFrom2D offset2D(double delta, boolean chamfer)
	{
		return module2DFrom2D(new Offset2D(delta, chamfer));
	}

	@Override
	public Module2DFrom2D offsetRound2D(double radius, int angularResolution)
	{
		return module2DFrom2D(new OffsetRound2D(radius, angularResolution));
	}
}
