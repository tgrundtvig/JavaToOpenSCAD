package org.abstractica.javatoopenscad.scad.stringutil;

import java.util.Locale;

public class Str
{
	static String f(double d, int decimals)
	{
		return String.format(Locale.ENGLISH,"%." + decimals + "f", d);
	}
}
