package org.abstractica.javatoopenscad.modulesimpl.common.text;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;

public class TextFont implements HasArguments
{
	public final String fontName;
	public final String fontStyle;
	public final String language;
	public final String script;

	public TextFont(String fontName, String fontStyle, String language, String script)
	{
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.language = language;
		this.script = script;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("fontName", fontName);
		collector.add("fontStyle", fontStyle);
		collector.add("language", language);
		collector.add("script", script);
	}
}
