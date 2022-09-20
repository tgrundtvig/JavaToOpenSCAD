package refactoring.modules.modulesimpl.builtin;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.modules.modulesintf.math.Angle;

public class RotateExtrude implements BuiltInModule
{
	private final Angle angle;
	private final int angularResolution;
	private final int convexity;

	public RotateExtrude(Angle angle, int angularResolution, int convexity)
	{
		this.angle = angle;
		this.angularResolution = angularResolution;
		this.convexity = convexity;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate_extrude(angle = " + angle.asDegrees() +
				", convexity = " + convexity +
				", $fn = " + angularResolution + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("angle", angle);
		collector.add("angularResolution", angularResolution);
		collector.add("convexity", convexity);
	}
}
