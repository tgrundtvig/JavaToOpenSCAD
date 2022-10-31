package org.abstractica.openscadcore.impl.operationsimpl;


import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Scale2DImpl extends AGeometry2DFrom2D
{
	private final double x;
	private final double y;

	public Scale2DImpl(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("scale([" + x + ", " + y + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
	}
}
