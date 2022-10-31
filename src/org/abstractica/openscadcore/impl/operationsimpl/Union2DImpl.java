package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Union2DImpl extends AGeometry2DFrom2D
{
	public Union2DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("union()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
