package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;

public interface Arrange2D
{
	//Arrangements
	Module2D lineOfModule2D(Module2D module, int count, double distance);
	Module2D circleOfModule2D(Module2D module, int count, double radius, boolean rotateModule);

	Module2DFrom2D circleOfModules2D(double radius, boolean rotateChildren);
}
