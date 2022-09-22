package org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.*;
import org.abstractica.javatoopenscad.csg.csg2d.Text2D;
import org.abstractica.javatoopenscad.modulesimpl.common.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.common.TextSize;
import org.abstractica.javatoopenscad.plugininterfaces.Module2DImpl;

public class TextLineup2D implements Module2DImpl
{
	private final String text;
	private final double letterDistance;
	private final TextFont font;
	private final TextSize size;
	private final int textResolution;

	public TextLineup2D(String text, double letterDistance, TextFont font, TextSize size, int textResolution)
	{
		this.text = text;
		this.letterDistance = letterDistance;
		this.font = font;
		this.size = size;
		this.textResolution = textResolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("text", text);
		collector.add("letterDistance", letterDistance);
		collector.add("font", font);
		collector.add("size", size);
		collector.add("textResolution", textResolution);
	}

	@Override
	public Module2D buildGeometry(CSG csg)
	{
		Text2D text2D = csg.csg2D().text2D();
		TextAlignment align = CSGText.textAlignment(TextHAlign.LEFT, TextVAlign.BOTTOM, TextDirection.LEFT_TO_RIGHT);
		Module2DFrom2D union = csg.csg2D().construct2D().union2D();
		for(int i = 0; i < text.length(); ++i)
		{
			Module2D letter = text2D.text("" + text.charAt(i), font, size, align,16);
			Module2DFrom2D translate = csg.csg2D().construct2D().translate2D(i*letterDistance, 0);
			translate.add(letter);
			union.add(translate);
		}
		return union;
	}
}
