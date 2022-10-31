package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Hull3DImpl extends AGeometry3DFrom3D
{
	public Hull3DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("hull()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
