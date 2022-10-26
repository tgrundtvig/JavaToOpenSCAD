package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.csg.csg2d.Shapes2D;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.shapes2d.*;

public class Shapes2DImpl extends AModuleFactory implements Shapes2D
{
	public Shapes2DImpl(CSGImpl csg)
	{
		super(csg);
	}

	@Override
	public Module2D unitSquare2D()
	{
		return module2D(UnitSquare2D.instance);
	}

	@Override
	public Module2D rect2D(double width, double height)
	{
		return module2D(new Rect2D(width, height));
	}

	@Override
	public Module2D rectCorners2D(Vector2D cornerA, Vector2D cornerB)
	{
		return module2D(new RectCorners2D(cornerA, cornerB));
	}

	@Override
	public Module2D unitCircle2D(int angularResolution)
	{
		return module2D(new UnitCircle2D(angularResolution));
	}

	@Override
	public Module2D circle2D(double diameter, int angularResolution)
	{
		return module2D(new Circle2D(diameter, angularResolution));
	}

	@Override
	public Module2D hollowCircle2D(double innerDiameter, double outerDiameter, int angularResolution)
	{
		return module2D(new HollowCircle2D(innerDiameter, outerDiameter, angularResolution));
	}

	@Override
	public Module2D ellipse2D(double diameterX, double diameterY, int angularResolution)
	{
		return module2D(new Ellipse2D(diameterX, diameterY, angularResolution));
	}

	@Override
	public Module2D isoscelesTriangle(double base, double height)
	{
		return module2D(new IsoscelesTriangle(base, height));
	}

	@Override
	public Module2D pie2D(double diameter, Angle start, Angle end, int steps)
	{
		return module2D(new Pie2D(diameter, start, end, steps));
	}

}
