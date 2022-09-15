package org.abstractica.javatoopenscad.scad.module;

public interface Arguments
{
	void getArguments(ArgumentCollector collector);
	String getClearText();
	int getUniqueId();
}
