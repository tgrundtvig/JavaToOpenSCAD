package org.abstractica.openscadcore.impl.operationsimpl.textimpl;

import org.abstractica.openscadcore.intf.text.TextSize;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.HasArguments;

public class TextSizeImpl implements TextSize, HasArguments
{
	private final double size;
	private final double spacing;

	public TextSizeImpl(double size, double spacing)
	{
		this.size = size;
		this.spacing = spacing;
	}

	@Override
	public double size()
	{
		return size;
	}

	@Override
	public double spacing()
	{
		return spacing;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(size);
		collector.add(spacing);
	}
}
