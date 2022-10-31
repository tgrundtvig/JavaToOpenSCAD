package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class RotateAndProject2DImpl extends AGeometry2DFrom2D
{
	private final double xDeg;
	private final double yDeg;
	private final double zDeg;

	public RotateAndProject2DImpl(double xDeg, double yDeg, double zDeg)
	{
		this.xDeg = xDeg;
		this.yDeg = yDeg;
		this.zDeg = zDeg;
	}

	public double xDeg()
	{
		return xDeg;
	}

	public double yDeg()
	{
		return yDeg;
	}

	public double zDeg()
	{
		return zDeg;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("rotate([" + xDeg + ", " + yDeg + ", " + zDeg + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(xDeg);
		collector.add(yDeg);
		collector.add(zDeg);
	}
}
