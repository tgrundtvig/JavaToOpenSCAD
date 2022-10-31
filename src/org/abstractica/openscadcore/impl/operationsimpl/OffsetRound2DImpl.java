package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class OffsetRound2DImpl extends AGeometry2DFrom2D
{
	private final double radius;
	private final int angularResolution;

	public OffsetRound2DImpl(double radius, int angularResolution)
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
		collector.add(radius);
		collector.add(angularResolution);
	}
}
