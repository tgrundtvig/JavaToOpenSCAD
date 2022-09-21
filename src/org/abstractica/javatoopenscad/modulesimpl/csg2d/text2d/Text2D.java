package org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;
import org.abstractica.javatoopenscad.csg.TextDirection;
import org.abstractica.javatoopenscad.csg.TextHAlign;
import org.abstractica.javatoopenscad.csg.TextVAlign;

public class Text2D implements BuiltInModule
{
	private final String text;
	private final TextFont font;
	private final TextSize size;
	private final TextAlignment alignment;
	private final int resolution;

	public Text2D(String text, TextFont font, TextSize size, TextAlignment alignment, int resolution)
	{
		this.text = text;
		this.font = font;
		this.size = size;
		this.alignment = alignment;
		this.resolution = resolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("text", text);
		collector.add("font", font);
		collector.add("size", size);
		collector.add("alignment", alignment);
		collector.add("resolution", resolution);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.println("text");
		cb.println("(");
		cb.indent();

		// text
		cb.print("text = \"");
		cb.print(text.replace("\"", "\\\""));
		cb.println("\",");

		// size
		cb.print("size = ");
		cb.print(Double.toString(size.size));
		cb.println(",");

		// font
		cb.print("font = \"");
		cb.print(font.fontName);
		cb.print(":style=");
		cb.print(font.fontStyle);
		cb.println("\",");

		// halign
		cb.print("halign = \"");
		cb.print(halign(alignment.halign));
		cb.println("\",");

		// valign
		cb.print("valign = \"");
		cb.print(valign(alignment.valign));
		cb.println("\",");

		// spacing
		cb.print("spacing = ");
		cb.print(Double.toString(size.spacing));
		cb.println(",");

		// direction
		cb.print("direction = \"");
		cb.print(direction(alignment.direction));
		cb.println("\",");

		// language
		cb.print("language = \"");
		cb.print(font.language);
		cb.println("\",");

		// script
		cb.print("script = \"");
		cb.print(font.script);
		cb.println("\",");

		// language
		cb.print("$fn = ");
		cb.println(Integer.toString(resolution));

		// end of text
		cb.undent();
		cb.println(");");
	}

	private String halign(TextHAlign halign)
	{
		return switch(halign)
		{
			case LEFT -> "left";
			case CENTER -> "center";
			case RIGHT -> "right";
		};
	}

	private String valign(TextVAlign valign)
	{
		return switch(valign)
		{
			case TOP -> "top";
			case CENTER -> "center";
			case BASELINE -> "baseline";
			case BOTTOM -> "bottom";
		};
	}

	private String direction(TextDirection direction)
	{
		return switch(direction)
		{
			case LEFT_TO_RIGHT -> "ltr";
			case RIGHT_TO_LEFT -> "rtl";
			case TOP_TO_BOTTOM -> "ttb";
			case BOTTOM_TO_TOP -> "btt";
		};
	}


}
