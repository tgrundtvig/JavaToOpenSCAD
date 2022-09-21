package org.abstractica.javatoopenscad.modules.modulesimpl.builtin;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class OffsetRound2D implements BuiltInModule
{
	private final double radius;
	private final int angularResolution;

	public OffsetRound2D(double radius, int angularResolution)
	{
		this.radius = radius;
		this.angularResolution = angularResolution;
	}

	public double getRadius()
	{
		return radius;
	}

	public int getAngularResolution()
	{
		return angularResolution;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("offset(r = " + radius +
				", $fn = " + angularResolution + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("radius", radius);
		collector.add("angularResolution", angularResolution);
	}
}
