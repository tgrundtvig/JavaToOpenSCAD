package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Offset2DImpl extends AGeometry2DFrom2D
{
	private final double delta;
	private final boolean chamfer;

	public Offset2DImpl(double delta, boolean chamfer)
	{
		this.delta = delta;
		this.chamfer = chamfer;
	}

	public double getDelta()
	{
		return delta;
	}

	public boolean getChamfer()
	{
		return chamfer;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("offset(delta = " + delta +
				", chamfer = " + chamfer + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(delta);
		collector.add(chamfer);
	}
}
