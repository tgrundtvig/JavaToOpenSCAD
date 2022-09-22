package org.abstractica.javatoopenscad.csg;

import org.abstractica.javatoopenscad.modulesimpl.common.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.common.TextSize;

public interface CSGText
{
	TextFont defaultFont = textFont("Courier New", "Regular");
	TextSize defaultSize = textSize(10, 1);
	TextAlignment defaultAlignment = textAlignment
			(
				TextHAlign.CENTER,
				TextVAlign.BASELINE,
				TextDirection.LEFT_TO_RIGHT
			);
	static TextFont textFont(String fontName, String fontStyle, String language, String script)
	{
		return new TextFont(fontName, fontStyle, language, script);
	}

	static TextFont textFont(String fontName, String fontStyle)
	{
		return textFont(fontName, fontStyle, "en", "latin");
	}

	static TextSize textSize(double size, double spacing)
	{
		return new TextSize(size, spacing);
	}

	static TextSize textSize(double size)
	{
		return textSize(size, 1);
	}

	static TextAlignment textAlignment(TextHAlign halign, TextVAlign valign, TextDirection direction)
	{
		return new TextAlignment(halign, valign, direction);
	}

}
