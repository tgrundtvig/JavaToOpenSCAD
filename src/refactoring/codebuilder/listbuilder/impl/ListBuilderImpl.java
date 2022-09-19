package refactoring.codebuilder.listbuilder.impl;

import refactoring.codebuilder.listbuilder.ListBuilder;
import refactoring.codebuilder.textoutput.TextOutput;

public class ListBuilderImpl implements ListBuilder
{
	private final TextOutput out;
	private final String listSeperator;
	private final String listEnd;
	private boolean firstItem;
	private boolean done;

	public ListBuilderImpl(TextOutput out, String listBegin, String listSeperator, String listEnd)
	{
		this.out = out;
		out.print(listBegin);
		this.listSeperator = listSeperator;
		this.listEnd = listEnd;
		firstItem = true;
		done = false;
	}

	@Override
	public ListBuilder add(String str)
	{
		if(done)
		{
			throw new RuntimeException("List has been closed!");
		}
		if(firstItem)
		{
			firstItem = false;
		}
		else
		{
			out.print(listSeperator);
		}
		out.print(str);
		return this;
	}

	@Override
	public void end()
	{
		out.print(listEnd);
		done = true;
	}
}
