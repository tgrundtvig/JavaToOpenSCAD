package org.abstractica.javatoopenscad.scadmodules.impl;

import org.abstractica.javatoopenscad.scad.module.Arguments;

public interface ArgumentCollector
{
	void add(String name, int value);
	void add(String name, double value);
	void add(String name, String value);
	void add(String name, Arguments arguments);
}
