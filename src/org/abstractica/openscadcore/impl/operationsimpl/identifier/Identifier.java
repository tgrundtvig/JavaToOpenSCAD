package org.abstractica.openscadcore.impl.operationsimpl.identifier;

import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.HasArguments;

import java.util.Locale;

public class Identifier implements ArgumentCollector
{
	private StringBuilder uniqueIdStr;
	private final int id;

	public Identifier(HasArguments item)
	{
		this.uniqueIdStr = new StringBuilder();
		uniqueIdStr.append(AllStrings.id(item.getClassName()));
		item.collectArguments(this);
		id = AllStrings.id(uniqueIdStr.toString());
		this.uniqueIdStr = null;
	}

	@Override
	public void add(boolean b)
	{
		uniqueIdStr.append("b").append(b ? 1 : 0);
	}

	@Override
	public void add(int i)
	{
		uniqueIdStr.append("i").append(AllStrings.id(Integer.toString(i)));
	}

	@Override
	public void add(double d)
	{
		if(Math.abs(d) < 0.0000000001) d = 0.0;
		String doubleString = String.format(Locale.ENGLISH,"%.10f", d);
		uniqueIdStr.append("d").append(AllStrings.id(doubleString));
	}

	@Override
	public void add(String str)
	{
		uniqueIdStr.append("s").append(AllStrings.id(str));
	}

	@Override
	public void add(HasArguments element)
	{
		Identifier identifier = new Identifier(element);
		uniqueIdStr.append("a").append(identifier.getUniqueId());
	}

	public int getUniqueId()
	{
		return id;
	}
}
