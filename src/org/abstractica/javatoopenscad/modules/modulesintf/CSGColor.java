package org.abstractica.javatoopenscad.modules.modulesintf;

import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.Color;

public interface CSGColor
{
	static Color color(double r, double g, double b, double a)
	{
		return new Color(r,g,b,a);
	}

	//ToDo add lots of ways to make colors
}
