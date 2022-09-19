package org.abstractica.javatoopenscad.scad.module.impl;

import refactoring.core.AllStrings;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.module.Arguments;

import java.util.Locale;

public class ArgumentCollectorImpl implements ArgumentCollector
{
	private final StringBuilder uniqueId;
	private final StringBuilder clearText;
	private boolean first = true;

	public ArgumentCollectorImpl(StringBuilder uniqueId, StringBuilder clearText)
	{
		this.uniqueId = uniqueId;
		this.clearText = clearText;
	}

	@Override
	public void add(String name, int i)
	{
		uniqueId.append("i").append(AllStrings.id(Integer.toString(i)));
		prefix(name);
		clearText.append(i);
	}

	@Override
	public void add(String name, double d)
	{
		uniqueId.append("d").append(AllStrings.id(Double.toString(d)));
		prefix(name);
		clearText.append(d(d));
	}

	@Override
	public void add(String name, String str)
	{
		uniqueId.append("s").append(AllStrings.id(str));
		prefix(name);
		clearText.append("\"");
		clearText.append(str);
		clearText.append("\"");
	}

	@Override
	public void add(String name, Arguments arguments)
	{
		uniqueId.append("a").append(AllStrings.id(Integer.toString(arguments.getUniqueId())));
		prefix(name);
		clearText.append(arguments.getClearText());
	}

	private String d(double d)
	{
		return String.format(Locale.ENGLISH, "%.4f", d);
	}

	private void prefix(String name)
	{
		if (first)
		{
			first = false;
		} else
		{
			clearText.append(", ");
		}
		clearText.append(name);
		clearText.append(" = ");
	}
}
