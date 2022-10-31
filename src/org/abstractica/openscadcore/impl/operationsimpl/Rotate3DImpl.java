package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Rotate3DImpl extends AGeometry3DFrom3D
{
	private final double xDeg;
	private final double yDeg;
	private final double zDeg;

	public Rotate3DImpl(double xDeg, double yDeg, double zDeg)
	{
		this.xDeg = xDeg;
		this.yDeg = yDeg;
		this.zDeg = zDeg;
	}

	public double getxDeg()
	{
		return xDeg;
	}

	public double getyDeg()
	{
		return yDeg;
	}

	public double getzDeg()
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
