package org.abstractica.javatoopenscad.modules.modulesimpl.csg3d.g3dfrom3d;

import org.abstractica.javatoopenscad.coreimpl.core.Module3DFrom3D;
import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.*;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom3d.G3DFrom3D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Polar3D;

public class G3DFrom3DImpl extends AModuleFactory implements G3DFrom3D
{
	public G3DFrom3DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module3DFrom3D color3D(Color color)
	{
		return module3DFrom3D(color);
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
}
