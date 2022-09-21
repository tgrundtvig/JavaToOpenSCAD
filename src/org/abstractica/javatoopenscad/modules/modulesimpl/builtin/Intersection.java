package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Intersection implements BuiltInModule
{
	public static final Intersection instance = new Intersection();

	private Intersection() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("intersection()");
	}

	@Override
	public void getArguments(ArgumentCollector collector) {}
}
