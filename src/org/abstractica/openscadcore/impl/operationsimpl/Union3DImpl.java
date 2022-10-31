package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Union3DImpl extends AGeometry3DFrom3D
{
	public Union3DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("union()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
