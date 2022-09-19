package refactoring.codebuilder;

import refactoring.codebuilder.blockbuilder.BlockBuilder;
import refactoring.codebuilder.listbuilder.ListBuilder;
import refactoring.codebuilder.textoutput.IndentedTextOutput;

public interface CodeBuilder extends IndentedTextOutput
{
	ListBuilder list();
	BlockBuilder block();
}
