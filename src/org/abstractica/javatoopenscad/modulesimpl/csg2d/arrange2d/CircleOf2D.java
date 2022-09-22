package org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Polar2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

public class CircleOf2D implements Module2DImpl
{
	private final Module2D module;
	private final int count;
	private final double radius;
	private final boolean rotateModule;

	public CircleOf2D(Module2D module2D, int count, double radius, boolean rotateModule)
	{
		this.module = module2D;
		this.count = count;
		this.radius = radius;
		this.rotateModule = rotateModule;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("module", module);
		collector.add("count", count);
		collector.add("radius", radius);
		collector.add("rotateModule", rotateModule);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		double segmentAngle = 1.0 / count;
		for(int i = 0; i < count; ++i)
		{
			if(rotateModule)
			{
				Module2DFrom2D translate = csg.csg2D().construct2D().translate2D(radius, 0);
				translate.add(module);
				Module2DFrom2D rotate = csg.csg2D().construct2D().rotate2D(Angle.rotations(i*segmentAngle));
				rotate.add(translate);
				union.add(rotate);
			}
			else
			{
				Module2DFrom2D translate = csg.csg2D().construct2D()
						.translate2D(Polar2D.create(radius, Angle.rotations(i*segmentAngle)));
				translate.add(module);
				union.add(translate);
			}

		}
		return union;
	}
}
