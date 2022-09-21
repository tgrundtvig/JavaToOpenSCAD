package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Offset2D implements BuiltInModule
{
	private final double delta;
	private final boolean chamfer;

	public Offset2D(double delta, boolean chamfer)
	{
		this.delta = delta;
		this.chamfer = chamfer;
	}

	public double getDelta()
	{
		return delta;
	}

	public boolean getChamfer()
	{
		return chamfer;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("offset(delta = " + delta +
				", chamfer = " + chamfer + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("delta", delta);
		collector.add("chamfer", chamfer);
	}
}
