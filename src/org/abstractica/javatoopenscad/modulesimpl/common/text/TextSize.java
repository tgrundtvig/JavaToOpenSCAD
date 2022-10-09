package org.abstractica.javatoopenscad.modulesimpl.common.text;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;

public class TextSize implements HasArguments
{
	public final double size;
	public final double spacing;

	public TextSize(double size, double spacing)
	{
		this.size = size;
		this.spacing = spacing;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{

	}
}
