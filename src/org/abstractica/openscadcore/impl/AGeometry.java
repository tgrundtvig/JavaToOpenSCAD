package org.abstractica.openscadcore.impl;

import org.abstractica.openscadcore.intf.Geometry;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

import java.util.Map;

public abstract class AGeometry implements Geometry, HasArguments
{
	private boolean debugMarked;
	private boolean disabled;

	public AGeometry()
	{
		debugMarked = false;
		disabled = false;
	}

	protected abstract void getArguments(ArgumentCollector collector);
	protected abstract void getCallHeader(CodeBuilder cb);
	protected abstract void doGenerateCall(CodeBuilder cb, Map<Integer, AModule> usedModules);

	public final void generateCall(CodeBuilder cb, Map<Integer, AModule> usedModules)
	{
		if(debugMarked)
		{
			cb.print("#");
		}
		if(disabled)
		{
			cb.print("%");
		}
		doGenerateCall(cb, usedModules);
	}

	@Override
	public void debugMark()
	{
		debugMarked = true;
	}

	@Override
	public void disable()
	{
		disabled = true;
	}
}
