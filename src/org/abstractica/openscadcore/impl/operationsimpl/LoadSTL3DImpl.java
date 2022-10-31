package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class LoadSTL3DImpl extends AGeometry3D
{
	private final String fileName;

	public LoadSTL3DImpl(String fileName)
	{
		this.fileName = fileName;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("import(\"" + fileName + "\", convexity=30)");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(fileName);
	}
}
