package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom2D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Resize2DImpl extends AGeometry2DFrom2D
{
	private final double x;
	private final double y;
	private final boolean autoX;
	private final boolean autoY;

	public Resize2DImpl(double x, double y, boolean autoX, boolean autoY)
	{
		this.x = x;
		this.y = y;
		this.autoX = autoX;
		this.autoY = autoY;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
		collector.add(autoX);
		collector.add(autoY);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("resize(newsize=[");
		cb.print(Double.toString(x));
		cb.print(",");
		cb.print(Double.toString(y));
		cb.print("], auto=[");
		cb.print(Boolean.toString(autoX));
		cb.print(",");
		cb.print(Boolean.toString(autoY));
		cb.print("])");
	}


}
