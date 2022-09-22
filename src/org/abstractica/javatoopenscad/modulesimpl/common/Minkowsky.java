package org.abstractica.javatoopenscad.modulesimpl.common;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Minkowsky implements BuiltInModule
{
	public static final Minkowsky instance = new Minkowsky();

	private Minkowsky() {}
	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("minkowsky()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
