package org.abstractica.javatoopenscad.csg;

import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAttributes;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextSize;

public interface CSGText
{
	TextFont defaultFont = textFont("Courier New", "Regular");
	TextSize defaultSize = textSize(10, 1);
	TextAlignment defaultAlignment = textAlignment
			(
				TextHAlign.CENTER,
				TextVAlign.BOTTOM,
				TextDirection.LEFT_TO_RIGHT
			);
	TextAttributes defaultAttributes = textAttributes(defaultFont, defaultSize, defaultAlignment);
	int defaultTextResolution = 16;

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

	static TextAttributes textAttributes(TextFont font, TextSize size, TextAlignment alignment)
	{
		return new TextAttributes(font, size, alignment);
	}

}
