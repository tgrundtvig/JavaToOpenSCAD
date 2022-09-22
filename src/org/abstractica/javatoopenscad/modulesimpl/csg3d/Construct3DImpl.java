package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.javatoopenscad.csg.csg3d.Polar3D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.javatoopenscad.modulesimpl.common.*;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.construct3d.Mirror3D;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.construct3d.Scale3D;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.construct3d.Translate3D;

public class Construct3DImpl extends AModuleFactory implements Construct3D
{
	public Construct3DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module3DFrom3D translate3D(double x, double y, double z)
	{
		return module3DFrom3D(new Translate3D(x, y, z));
	}

	@Override
	public Module3DFrom3D translate3D(Vector3D t)
	{
		return translate3D(t.x(), t.y(), t.z());
	}

	@Override
	public Module3DFrom3D translate3D(Polar3D p)
	{
		return translate3D(p.asVector3D());
	}

	@Override
	public Module3DFrom3D rotate3D(Angle x, Angle y, Angle z)
	{
		return module3DFrom3D(new Rotate(x, y, z));
	}

	@Override
	public Module3DFrom3D scale3D(double x, double y, double z)
	{
		return module3DFrom3D(new Scale3D(x, y, z));
	}

	@Override
	public Module3DFrom3D scale3D(Vector3D s)
	{
		return scale3D(s.x(), s.y(), s.z());
	}

	@Override
	public Module3DFrom3D mirror3D(double normX, double normY, double normZ)
	{
		return module3DFrom3D(new Mirror3D(normX, normY, normZ));
	}

	@Override
	public Module3DFrom3D mirror3D(Vector3D norm)
	{
		return mirror3D(norm.x(), norm.y(), norm.z());
	}

	@Override
	public Module3DFrom3D mirror3D(Polar3D norm)
	{
		return mirror3D(norm.asVector3D());
	}

	@Override
	public Module3DFrom3D union3D()
	{
		return module3DFrom3D(Union.instance);
	}

	@Override
	public Module3DFrom3D intersection3D()
	{
		return module3DFrom3D(Intersection.instance);
	}

	@Override
	public Module3DFrom3D difference3D()
	{
		return module3DFrom3D(Difference.instance);
	}

	@Override
	public Module3DFrom3D hull3D()
	{
		return module3DFrom3D(Hull.instance);
	}

	@Override
	public Module3DFrom3D minkowsky3D()
	{
		return module3DFrom3D(Minkowsky.instance);
	}

	@Override
	public Module3DFrom2D linearExtrude(double height, Angle twist, double scale, int slices, int convexity)
	{
		return module3DFrom2D(new LinearExtrude(height, twist, scale, slices, convexity));
	}

	@Override
	public Module3DFrom2D linearExtrude(double height, int convexity)
	{
		return linearExtrude(height, Angle.ZERO, 1, 1, convexity);
	}

	@Override
	public Module3DFrom2D linearExtrude(double height, double scale, int convexity)
	{
		return linearExtrude(height, Angle.ZERO, scale, 1, convexity);
	}

	@Override
	public Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity)
	{
		return module3DFrom2D(new RotateExtrude(angle, angularResolution, convexity));
	}
}
