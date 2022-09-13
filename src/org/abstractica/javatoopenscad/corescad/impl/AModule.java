package org.abstractica.javatoopenscad.corescad.impl;

public abstract class AModule extends AGeometry
{
	private final String name;

	public AModule(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
