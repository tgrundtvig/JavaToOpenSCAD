package org.abstractica.javatoopenscad.modulesimpl.common;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;
import org.abstractica.javatoopenscad.csg.Angle;

public class Rotate implements BuiltInModule
{
	private final Angle x;
	private final Angle y;
	private final Angle z;

	public Rotate(Angle x, Angle y, Angle z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Angle x()
	{
		return x;
	}

	public Angle y()
	{
		return y;
	}

	public Angle z()
	{
		return z;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate([" + x.asDegrees() + ", " + y.asDegrees() + ", " + z.asDegrees() + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
	}
}
