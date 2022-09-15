package org.abstractica.javatoopenscad.scad.module;

import org.abstractica.javatoopenscad.scadmodules.impl.ArgumentCollector;

public interface Arguments
{
	void getArguments(ArgumentCollector collector);
	String getClearText();
	int getUniqueId();
}
