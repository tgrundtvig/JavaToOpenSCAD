package org.abstractica.javatoopenscad.modulesimpl.common.text;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;
import org.abstractica.javatoopenscad.csg.TextDirection;
import org.abstractica.javatoopenscad.csg.TextHAlign;
import org.abstractica.javatoopenscad.csg.TextVAlign;

public class TextAlignment implements HasArguments
{
	public final TextHAlign halign;
	public final TextVAlign valign;
	public final TextDirection direction;

	public TextAlignment(TextHAlign halign, TextVAlign valign, TextDirection direction)
	{
		this.halign = halign;
		this.valign = valign;
		this.direction = direction;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("halign", halign.toString());
		collector.add("valign", valign.toString());
		collector.add("direction", direction.toString());
	}
}
