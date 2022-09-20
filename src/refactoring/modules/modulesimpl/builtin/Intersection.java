package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Intersection implements BuiltInModule
{
	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("intersection()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
