package org.abstractica.javatoopenscad.scad.module;

public abstract class AArguments implements Arguments
{
	private int uniqueId;
	private String clearText;

	public AArguments()
	{
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
		uniqueId.append(AllStrings.id(getClass().getName()));
		StringBuilder clearText = new StringBuilder();
		clearText.append(getClass().getSimpleName());
		clearText.append("(");
		ArgumentCollectorImpl collector = new ArgumentCollectorImpl(uniqueId, clearText);
		getArguments(collector);
		clearText.append(")");
		this.uniqueId = AllStrings.id(uniqueId.toString());
		this.clearText = clearText.toString();
	}
}
