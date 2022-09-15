package org.abstractica.javatoopenscad.util;

import java.util.Locale;

public class Str
{
	public static String f(double d, int decimals)
	{
		return String.format(Locale.ENGLISH,"%." + decimals + "f", d);
	}
}
