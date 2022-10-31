package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Minkowsky2DImpl extends AGeometry2DFrom2D
{
	public Minkowsky2DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("minkowsky()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
