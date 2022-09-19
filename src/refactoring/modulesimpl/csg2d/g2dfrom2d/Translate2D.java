package refactoring.modulesimpl.csg2d.g2dfrom2d;

import refactoring.core.BuiltInModule;
import refactoring.modules.plugininterfaces.ArgumentCollector;

public class Translate2D implements BuiltInModule
{
	private final double x;
	private final double y;

	public Translate2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String getCallHeader()
	{
		return "translate(" + x + ", " + y + ")";
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
	}
}
