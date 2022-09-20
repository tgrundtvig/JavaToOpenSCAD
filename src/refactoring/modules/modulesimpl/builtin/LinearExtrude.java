package refactoring.modules.modulesimpl.builtin;
import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.modules.modulesintf.math.Angle;

public class LinearExtrude implements BuiltInModule
{
	private final double height;
	private final Angle twist;
	private final double scale;
	private final int slices;
	private final int convexity;

	public LinearExtrude(double height, Angle twist, double scale, int slices, int convexity)
	{
		this.height = height;
		this.twist = twist;
		this.scale = scale;
		this.slices = slices;
		this.convexity = convexity;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("height", height);
		collector.add("twist", twist);
		collector.add("scale", scale);
		collector.add("slices", slices);
		collector.add("convexity", convexity);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("linear_extrude(height = " + height +
				", center = true, twist = " + twist.asDegrees() +
				", scale = " + scale +
				", slices = " + slices +
				", convexity = " + convexity + ")");
	}


}
