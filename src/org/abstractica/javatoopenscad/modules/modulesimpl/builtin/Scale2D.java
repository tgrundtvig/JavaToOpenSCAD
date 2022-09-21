package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Scale2D implements BuiltInModule
{
	private final double x;
	private final double y;

	public Scale2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("scale([" + x + ", " + y + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
	}
}
