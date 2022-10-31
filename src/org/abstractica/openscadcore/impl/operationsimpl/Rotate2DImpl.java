package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Rotate2DImpl extends AGeometry2DFrom2D
{
	private final double deg;

	public Rotate2DImpl(double deg)
	{
		this.deg = deg;
	}

	public double deg()
	{
		return deg;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate(" + deg + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(deg);
	}
}
