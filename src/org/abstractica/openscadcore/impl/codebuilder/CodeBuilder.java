package org.abstractica.openscadcore.impl.codebuilder;

import org.abstractica.openscadcore.impl.codebuilder.blockbuilder.BlockBuilder;
import org.abstractica.openscadcore.impl.codebuilder.listbuilder.ListBuilder;
import org.abstractica.openscadcore.impl.codebuilder.textoutput.IndentedTextOutput;

public interface CodeBuilder extends IndentedTextOutput
{
	ListBuilder list();
	BlockBuilder block();
}
