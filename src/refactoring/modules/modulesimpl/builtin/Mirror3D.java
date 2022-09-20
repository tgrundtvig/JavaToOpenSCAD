package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

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
