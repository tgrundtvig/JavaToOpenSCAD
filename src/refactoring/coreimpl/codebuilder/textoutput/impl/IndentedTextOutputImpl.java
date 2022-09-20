package refactoring.coreimpl.codebuilder.textoutput.impl;

import refactoring.coreimpl.codebuilder.textoutput.IndentedTextOutput;
import refactoring.coreimpl.codebuilder.textoutput.TextOutput;

public class IndentedTextOutputImpl implements IndentedTextOutput
{
	private final TextOutput out;
	private final String indentString;
	private StringBuilder curLine;
	private int indent;


	public IndentedTextOutputImpl(TextOutput out)
	{
		this(out, "    ");
	}

	public IndentedTextOutputImpl(TextOutput out, String indentString)
	{
		this.out = out;
		this.indentString = indentString;
		indent = 0;
		curLine = new StringBuilder();
	}


	@Override
	public void indent()
	{
		++indent;
	}

	@Override
	public void undent()
	{
		--indent;
		if(indent < 0)
		{
			throw new RuntimeException("Unbalanced indents!");
		}
	}

	@Override
	public void print(String str)
	{
		curLine.append(str);
	}

	@Override
	public void println(String str)
	{
		print(str);
		println();
	}

	@Override
	public void println()
	{
		out.print(indentString.repeat(indent));
		out.print(curLine.toString());
		out.println();
		curLine = new StringBuilder();
	}

	@Override
	public String toString()
	{
		if(curLine == null)
		{
			return out.toString();
		}
		else
		{
			return out.toString() + curLine.toString();
		}
	}
}
