package org.abstractica.javatoopenscad.generators.impl;

import org.abstractica.javatoopenscad.generators.CodeBuilder;

public class CodeBuilderImpl implements CodeBuilder
{
	private int indent;
	private StringBuilder curLine;
	private final StringBuilder res;

	public CodeBuilderImpl()
	{
		this.indent = 0;
		this.res = new StringBuilder();
		this.curLine = null;
	}

	@Override
	public void indent()
	{
		++indent;
	}

	@Override
	public void print(String str)
	{
		if(curLine == null)
		{
			curLine = new StringBuilder();
		}
		curLine.append(str);
	}

	@Override
	public void println(String line)
	{
		res.append("    ".repeat(indent));
		if(curLine != null)
		{
			res.append(curLine.toString());
			curLine = null;
		}
		res.append(line);
		res.append(System.lineSeparator());
	}

	@Override
	public void println()
	{
		println("");
	}

	@Override
	public void undent()
	{
		--indent;
	}

	@Override
	public String toString()
	{
		return res.toString();
	}
}
