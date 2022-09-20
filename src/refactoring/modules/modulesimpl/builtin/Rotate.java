package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.modules.modulesintf.math.Angle;

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
