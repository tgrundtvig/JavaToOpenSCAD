package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Intersection2DImpl extends AGeometry2DFrom2D
{
	public Intersection2DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("intersection()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
