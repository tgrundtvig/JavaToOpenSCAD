package org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.javatoopenscad.csg.math.Polar2D;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DFrom2DImpl;
import org.abstractica.javatoopenscad.csg.CSG;

import java.util.List;

public class CircleOfModules2D implements Module2DFrom2DImpl
{
	private final double radius;
	private final boolean rotateChildren;

	public CircleOfModules2D(double radius, boolean rotateChildren)
	{
		this.radius = radius;
		this.rotateChildren = rotateChildren;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("radius", radius);
		collector.add("rotateChildren", rotateChildren);
	}

	@Override
	public Module2DFrom2D buildGeometry(CSG csg, List<Module2D> children)
	{
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		if(children.isEmpty())
		{
			return union;
		}
		double segmentAngle = 1.0 / children.size();
		for(int i = 0; i < children.size(); ++i)
		{
			if(rotateChildren)
			{
				Module2DFrom2D translate = csg.csg2D().construct2D().translate2D(radius, 0);
				translate.add(children.get(i));
				Module2DFrom2D rotate = csg.csg2D().construct2D().rotate2D(Angle.rotations(i*segmentAngle));
				rotate.add(translate);
				union.add(rotate);
			}
			else
			{
				Module2DFrom2D translate = csg.csg2D().construct2D()
						.translate2D(Polar2D.create(radius, Angle.rotations(i * segmentAngle)));
				translate.add(children.get(i));
				union.add(translate);
			}
		}
		return union;
	}
}
