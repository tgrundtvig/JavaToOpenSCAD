package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

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
