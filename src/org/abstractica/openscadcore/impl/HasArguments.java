package org.abstractica.openscadcore.impl;

public interface HasArguments
{
	void collectArguments(ArgumentCollector collector);
	default String getClassName() {return getClass().getName();}
}
