package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg2d.Arrange2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d.CircleOf2D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d.LineOf2D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.arrange2d.CircleOfModules2D;

public class Arrange2DImpl extends AModuleFactory implements Arrange2D
{
	public Arrange2DImpl(CSGImpl csg)
	{
		super(csg);
	}

	@Override
	public Module2D lineOfModule2D(Module2D module2D, int count, double distance)
	{
		return module2D(new LineOf2D(module2D, count, distance));
	}

	@Override
	public Module2D circleOfModule2D(Module2D module, int count, double radius, boolean rotateModule)
	{
		return module2D(new CircleOf2D(module, count, radius, rotateModule));
	}

	@Override
	public Module2DFrom2D circleOfModules2D(double radius, boolean rotateChildren)
	{
		return module2DFrom2D(new CircleOfModules2D(radius, rotateChildren));
	}
}
