package org.abstractica.javatoopenscad.coreimpl.codebuilder;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.blockbuilder.BlockBuilder;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.listbuilder.ListBuilder;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.textoutput.IndentedTextOutput;

public interface CodeBuilder extends IndentedTextOutput
{
	ListBuilder list();
	BlockBuilder block();
}
