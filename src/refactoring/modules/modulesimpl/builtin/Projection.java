package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Projection implements BuiltInModule
{
	private final boolean cut;

	public Projection(boolean cut)
	{
		this.cut = cut;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("projection(cut = " + cut + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("cut", cut);
	}
}
