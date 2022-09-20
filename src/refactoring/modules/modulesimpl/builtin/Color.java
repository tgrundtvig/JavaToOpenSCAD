package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.coreimpl.core.HasArguments;

public class Color implements HasArguments, BuiltInModule
{
	private final double r, g, b, a;

	public Color(double r, double g, double b, double a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public double r()
	{
		return r;
	}
	public double g()
	{
		return g;
	}
	public double b()
	{
		return b;
	}
	public double a()
	{
		return a;
	}

	@Override
	public String toString()
	{
		return "Color(" +
				"r=" + r +
				", g=" + g +
				", b=" + b +
				", a=" + a +
				')';
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("r", r);
		collector.add("g", g);
		collector.add("b", b);
		collector.add("a", a);
	}

	@Override
	public String getSimpleName()
	{
		return "Color";
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("color([" +
				r  + ", " +
				g + ", " +
				b + "], " +
				a + ")");
	}
}
