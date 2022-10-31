package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

import java.util.Map;

public abstract class AGeometryLeaf extends AGeometry
{
	@Override
	public final void collectArguments(ArgumentCollector collector)
	{
		collector.add(getClassName());
		getArguments(collector);
	}

	@Override
	protected void doGenerateCall(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		getCallHeader(cb);
		cb.println(";");
	}
}
