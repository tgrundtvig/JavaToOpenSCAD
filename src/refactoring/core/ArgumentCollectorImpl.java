package refactoring.core;

import refactoring.modules.plugininterfaces.ArgumentCollector;

import java.util.Locale;

public class ArgumentCollectorImpl implements ArgumentCollector, Identifier
{
	private StringBuilder uniqueId;
	private StringBuilder clearText;
	private final String simpleName;
	private final String fullName;
	private final String nameWithArguments;
	private final int id;
	private boolean first;

	public ArgumentCollectorImpl(HasArguments item)
	{
		this.simpleName = item.getClass().getSimpleName();
		this.fullName = item.getClass().getName();
		this.uniqueId = new StringBuilder();
		this.clearText = new StringBuilder();
		first = true;
		uniqueId.append(AllStrings.id(item.getClass().getName()));
		clearText.append(item.getClass().getSimpleName());
		clearText.append("(");
		item.getArguments(this);
		clearText.append(")");
		nameWithArguments = clearText.toString();
		id = AllStrings.id(uniqueId.toString());
		uniqueId = null;
		clearText = null;
	}

	@Override
	public void add(String name, boolean b)
	{
		uniqueId.append("b").append(b ? 1 : 0);
		prefix(name);
		clearText.append(b);
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
	public void add(String name, BaseModule module)
	{
		uniqueId.append("m").append(module.getIdentifier().getUniqueId());
		prefix(name);
		clearText.append(module.getIdentifier().getSimpleName());
	}


	@Override
	public void add(String name, HasArguments element)
	{
		ArgumentCollectorImpl col = new ArgumentCollectorImpl(element);
		uniqueId.append("a").append(col.uniqueId);
		prefix(name);
		clearText.append(col.nameWithArguments);
	}

	@Override
	public String getFullName()
	{
		return fullName;
	}

	@Override
	public String getSimpleName()
	{
		return simpleName;
	}

	@Override
	public String getSimpeNameWithArguments()
	{
		return nameWithArguments;
	}

	public int getUniqueId()
	{
		return id;
	}

	public String getClearText()
	{
		return clearText.toString();
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
