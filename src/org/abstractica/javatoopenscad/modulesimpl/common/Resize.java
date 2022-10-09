package org.abstractica.javatoopenscad.modulesimpl.common;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;

public class Resize implements BuiltInModule
{
	private final double x;
	private final double y;
	private final double z;
	private final boolean autoX;
	private final boolean autoY;
	private final boolean autoZ;

	public Resize(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.autoX = autoX;
		this.autoY = autoY;
		this.autoZ = autoZ;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("x", x);
		collector.add("y", y);
		collector.add("z", z);
		collector.add("autoX", autoX);
		collector.add("autoY", autoY);
		collector.add("autoZ", autoZ);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("resize(newsize=[");
		cb.print(Double.toString(x));
		cb.print(",");
		cb.print(Double.toString(y));
		cb.print(",");
		cb.print(Double.toString(z));
		cb.print("], auto=[");
		cb.print(Boolean.toString(autoX));
		cb.print(",");
		cb.print(Boolean.toString(autoY));
		cb.print(",");
		cb.print(Boolean.toString(autoZ));
		cb.print("])");
	}


}
