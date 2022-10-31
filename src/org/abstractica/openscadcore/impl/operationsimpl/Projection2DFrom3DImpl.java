package org.abstractica.openscadcore.impl.operationsimpl;

import org.abstractica.openscadcore.impl.AGeometry2DFrom3D;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;

public class Projection2DFrom3DImpl extends AGeometry2DFrom3D
{
	private final boolean cutAtZeroZ;

	public Projection2DFrom3DImpl(boolean cutAtZeroZ)
	{
		this.cutAtZeroZ = cutAtZeroZ;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("projection(cut = " + cutAtZeroZ + ")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(cutAtZeroZ);
	}
}
