package org.abstractica.javatoopenscad.modulesimpl.common.text;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;

public class TextAttributes implements HasArguments
{
	public final TextFont font;
	public final TextSize size;
	public final TextAlignment alignment;

	public TextAttributes(TextFont font, TextSize size, TextAlignment alignment)
	{
		this.font = font;
		this.size = size;
		this.alignment = alignment;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("font", font);
		collector.add("size", size);
		collector.add("alignment", alignment);
	}
}
