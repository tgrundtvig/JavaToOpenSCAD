package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Mirror3D implements BuiltInModule
{
	private final double normX;
	private final double normY;
	private final double normZ;

	public Mirror3D(double normX, double normY, double normZ)
	{
		this.normX = normX;
		this.normY = normY;
		this.normZ = normZ;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("mirror([" + normX + ", " + normY + ", " + normZ + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("normX", normX);
		collector.add("normY", normY);
		collector.add("normZ", normZ);
	}
}
