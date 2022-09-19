package org.abstractica.javatoopenscad.scad.module.impl;

import refactoring.core.AllStrings;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;
import org.abstractica.javatoopenscad.scad.module.Arguments;
import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentsImplementation;

public class ArgumentsImpl implements Arguments
{
	private final ArgumentsImplementation impl;
	private int uniqueId;
	private String clearText;

	public ArgumentsImpl(ArgumentsImplementation impl)
	{
		this.impl = impl;
		uniqueId = -1;
		clearText = null;
	}

	public int getUniqueId()
	{
		if(uniqueId == -1)
		{
			generateUniqueIDAndClearText();
		}
		return uniqueId;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		impl.getArguments(collector);
	}

	public String getClearText()
	{
		if(clearText == null)
		{
			generateUniqueIDAndClearText();
		}
		return clearText;
	}

	private void generateUniqueIDAndClearText()
	{
		StringBuilder uniqueId = new StringBuilder();
		uniqueId.append(AllStrings.id(impl.getClass().getName()));
		StringBuilder clearText = new StringBuilder();
		clearText.append(impl.getClass().getSimpleName());
		clearText.append("(");
		ArgumentCollectorImpl collector = new ArgumentCollectorImpl(uniqueId, clearText);
		getArguments(collector);
		clearText.append(")");
		this.uniqueId = AllStrings.id(uniqueId.toString());
		this.clearText = clearText.toString();
	}
}
