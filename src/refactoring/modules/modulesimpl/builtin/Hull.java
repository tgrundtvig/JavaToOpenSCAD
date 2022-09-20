package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Hull implements BuiltInModule
{
	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("hull()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
