package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Union implements BuiltInModule
{
	public static final Union instance = new Union();

	private Union() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("union()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
