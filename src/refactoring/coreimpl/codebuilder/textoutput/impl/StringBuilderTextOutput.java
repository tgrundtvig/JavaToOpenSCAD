package refactoring.coreimpl.codebuilder.textoutput.impl;

import refactoring.coreimpl.codebuilder.textoutput.TextOutput;

public class StringBuilderTextOutput implements TextOutput
{
	private final StringBuilder strBuilder;

	public StringBuilderTextOutput()
	{
		this.strBuilder = new StringBuilder();
	}

	@Override
	public void print(String str)
	{
		strBuilder.append(str);
	}

	@Override
	public void println(String str)
	{
		strBuilder.append(str);
		strBuilder.append(System.lineSeparator());
	}

	@Override
	public void println()
	{
		strBuilder.append(System.lineSeparator());
	}

	@Override
	public String toString()
	{
		return strBuilder.toString();
	}
}
