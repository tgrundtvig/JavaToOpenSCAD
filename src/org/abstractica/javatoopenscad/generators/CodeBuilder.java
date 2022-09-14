package org.abstractica.javatoopenscad.generators;

public interface CodeBuilder
{
	void indent();
	void print(String str);
	void println(String line);
	void println();
	void undent();
}
