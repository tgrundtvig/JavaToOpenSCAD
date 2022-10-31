package org.abstractica.openscadcore.impl.codebuilder.textoutput;

public interface IndentedTextOutput extends TextOutput
{
	void indent();
	void undent();
}
