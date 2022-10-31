package org.abstractica.openscadcore.impl.operationsimpl.polygonimpl;

import org.abstractica.openscadcore.intf.polygon.Vector2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.HasArguments;

public class Vector2DImpl implements Vector2D, HasArguments
{
	private final double x;
	private final double y;

	public Vector2DImpl(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public double x()
	{
		return x;
	}

	@Override
	public double y()
	{
		return y;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
	}
}
