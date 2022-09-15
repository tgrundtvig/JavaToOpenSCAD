package org.abstractica.javatoopenscad.scad.impl;


import org.abstractica.javatoopenscad.scad.module.AArguments;
import org.abstractica.javatoopenscad.scad.module.ArgumentCollector;

public class Color extends AArguments
{
	public static final Color RED = new Color(1,0,0,1);
	public static Color create(double r, double g, double b, double a)
	{
		return new Color(r,g,b,a);
	}

	static Color create(double r, double g, double b)
	{
		return new Color(r,g,b,1);
	}
	private final double r, g, b, a;

	private Color(double r, double g, double b, double a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public double r()
	{
		return r;
	}
	public double g()
	{
		return g;
	}
	public double b()
	{
		return b;
	}
	public double a()
	{
		return a;
	}

	@Override
	public String toString()
	{
		return "Color(" +
				"r=" + r +
				", g=" + g +
				", b=" + b +
				", a=" + a +
				'}';
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("r", r);
		collector.add("g", g);
		collector.add("b", b);
		collector.add("a", a);
	}
}
