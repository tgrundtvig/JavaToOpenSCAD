package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;

public class Mirror2D implements BuiltInModule
{
	private final double normX;
	private final double normY;

	public Mirror2D(double normX, double normY)
	{
		this.normX = normX;
		this.normY = normY;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("mirror([" + normX + ", " + normY + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("normX", normX);
		collector.add("normY", normY);
	}
}
