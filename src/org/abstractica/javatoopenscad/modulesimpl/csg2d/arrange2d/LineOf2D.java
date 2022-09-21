package org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;

public class LineOf2D implements Module2DImpl
{
	private final Module2D module;
	private final int count;
	private final double distance;

	public LineOf2D(Module2D module, int count, double distance)
	{
		this.module = module;
		this.count = count;
		this.distance = distance;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("module", module);
		collector.add("count", count);
		collector.add("distance", distance);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		CSG2D csg2D = csg.csg2D();
		Module2DFrom2D union = csg2D.construct2D().union2D();
		double start = -0.5*((count-1) * distance);
		for(int i = 0; i < count; ++i)
		{
			Module2DFrom2D translate = csg2D.construct2D().translate2D(start + (i*distance), 0);
			translate.add(module);
			union.add(translate);
		}
		return union;
	}
}
