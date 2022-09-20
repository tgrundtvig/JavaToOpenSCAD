package refactoring.coreimpl.codebuilder;

import refactoring.coreimpl.codebuilder.blockbuilder.BlockBuilder;
import refactoring.coreimpl.codebuilder.listbuilder.ListBuilder;
import refactoring.coreimpl.codebuilder.textoutput.IndentedTextOutput;

public interface CodeBuilder extends IndentedTextOutput
{
	ListBuilder list();
	BlockBuilder block();
}
