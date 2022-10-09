package org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.BuiltInModule;
import org.abstractica.javatoopenscad.csg.TextDirection;
import org.abstractica.javatoopenscad.csg.TextHAlign;
import org.abstractica.javatoopenscad.csg.TextVAlign;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAttributes;

public class Text2DModuleImpl implements BuiltInModule
{
	private final String text;
	private final TextAttributes attributes;
	private final int resolution;

	public Text2DModuleImpl(String text, TextAttributes attributes, int resolution)
	{
		this.text = text;
		this.attributes = attributes;
		this.resolution = resolution;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("text", text);
		collector.add("attributes", attributes);
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
		cb.print(Double.toString(attributes.size.size));
		cb.println(",");

		// font
		cb.print("font = \"");
		cb.print(attributes.font.fontName);
		cb.print(":style=");
		cb.print(attributes.font.fontStyle);
		cb.println("\",");

		// halign
		cb.print("halign = \"");
		cb.print(halign(attributes.alignment.halign));
		cb.println("\",");

		// valign
		cb.print("valign = \"");
		cb.print(valign(attributes.alignment.valign));
		cb.println("\",");

		// spacing
		cb.print("spacing = ");
		cb.print(Double.toString(attributes.size.spacing));
		cb.println(",");

		// direction
		cb.print("direction = \"");
		cb.print(direction(attributes.alignment.direction));
		cb.println("\",");

		// language
		cb.print("language = \"");
		cb.print(attributes.font.language);
		cb.println("\",");

		// script
		cb.print("script = \"");
		cb.print(attributes.font.script);
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
