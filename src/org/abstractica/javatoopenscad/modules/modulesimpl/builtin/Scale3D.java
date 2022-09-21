package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Scale3D implements BuiltInModule
{
	private final double x;
	private final double y;
	private final double z;

	public Scale3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("scale([" + x + ", " + y + ", " + z + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
	}
}
