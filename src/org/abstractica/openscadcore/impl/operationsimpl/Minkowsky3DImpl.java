package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Minkowsky3DImpl extends AGeometry3DFrom3D
{
	public Minkowsky3DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("minkowsky()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
