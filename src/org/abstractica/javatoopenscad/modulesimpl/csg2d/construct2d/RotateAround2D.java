package org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DFrom2DImpl;

import java.util.List;

public class RotateAround2D implements Module2DFrom2DImpl
{
	private final Angle a;
	private final double px;
	private final double py;

	public RotateAround2D(Angle a, double px, double py)
	{
		this.a = a;
		this.px = px;
		this.py = py;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("a", a);
		collector.add("px", px);
		collector.add("py", py);
	}

	@Override
	public Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children)
	{
		Module2DFrom2D t1 = csg.csg2D().construct2D().translate2D(-px, -py);
		for(Module2D child : children)
		{
			t1.add(child);
		}
		Module2DFrom2D rotate = csg.csg2D().construct2D().rotate2D(a).add(t1);
		Module2DFrom2D t2 = csg.csg2D().construct2D().translate2D(px, py).add(rotate);
		return t2;
	}
}
