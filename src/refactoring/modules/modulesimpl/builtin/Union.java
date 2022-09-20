package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Union implements BuiltInModule
{
	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("union()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
