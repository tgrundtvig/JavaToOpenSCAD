package org.abstractica.javatoopenscad.generators;

import java.util.Locale;

public interface CodeBuilder
{
	void indent();
	void print(String str);
	void println(String line);
	void println();
	void undent();

	/*
	static String f(double d, int decimals)
	{
		return String.format(Locale.ENGLISH,"%." + decimals + "f", d);
	}
	 */
}
