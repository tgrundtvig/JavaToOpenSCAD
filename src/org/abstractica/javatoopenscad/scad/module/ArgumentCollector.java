package org.abstractica.javatoopenscad.scad.module;

public interface ArgumentCollector
{
	void add(String name, int value);
	void add(String name, double value);
	void add(String name, String value);
	void add(String name, Arguments arguments);
}
