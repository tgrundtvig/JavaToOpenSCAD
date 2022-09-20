package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Translate3D implements BuiltInModule
{
	private final double x;
	private final double y;
	private final double z;

	public Translate3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("translate([" + x + ", " + y + ", " + z + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
	}
}
