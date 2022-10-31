package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry3DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Scale3DImpl extends AGeometry3DFrom3D
{
	private final double x;
	private final double y;
	private final double z;

	public Scale3DImpl(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("scale([" + x + ", " + y + ", " + z + "])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
		collector.add(z);
	}
}
