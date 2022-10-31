package org.abstractica.openscadcore.impl.codebuilder.impl;

import org.abstractica.openscadcore.impl.codebuilder.CodeBuilder;
import org.abstractica.openscadcore.impl.codebuilder.blockbuilder.BlockBuilder;
import org.abstractica.openscadcore.impl.codebuilder.blockbuilder.BlockBuilderImpl;
import org.abstractica.openscadcore.impl.codebuilder.listbuilder.ListBuilder;
import org.abstractica.openscadcore.impl.codebuilder.listbuilder.impl.ListBuilderImpl;
import org.abstractica.openscadcore.impl.codebuilder.textoutput.IndentedTextOutput;
import org.abstractica.openscadcore.impl.codebuilder.textoutput.TextOutput;
import org.abstractica.openscadcore.impl.codebuilder.textoutput.impl.IndentedTextOutputImpl;

public class CodeBuilderImpl implements CodeBuilder
{
	private final IndentedTextOutput out;
	private final String blockBegin;
	private final String blockEnd;
	private final String listBegin;
	private final String listSeperator;
	private final String listEnd;
	public CodeBuilderImpl( TextOutput out,
							String indent,
                            String blockBegin,
							String blockEnd,
							String listBegin,
							String listSeperator,
							String listEnd)
	{
		this.out = new IndentedTextOutputImpl(out);
		this.blockBegin = blockBegin;
		this.blockEnd = blockEnd;
		this.listBegin = listBegin;
		this.listSeperator = listSeperator;
		this.listEnd = listEnd;
	}

	@Override
	public ListBuilder list()
	{
		return new ListBuilderImpl(this, listBegin, listSeperator, listEnd);
	}

	@Override
	public BlockBuilder block()
	{
		return new BlockBuilderImpl(this, blockBegin, blockEnd);
	}

	@Override
	public void indent()
	{
		out.indent();
	}

	@Override
	public void undent()
	{
		out.undent();
	}

	@Override
	public void print(String str)
	{
		out.print(str);
	}

	@Override
	public void println(String str)
	{
		out.println(str);
	}

	@Override
	public void println()
	{
		out.println();
	}

	@Override
	public String toString()
	{
		return out.toString();
	}
}
