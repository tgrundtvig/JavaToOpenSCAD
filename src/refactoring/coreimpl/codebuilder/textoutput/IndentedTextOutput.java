package refactoring.coreimpl.codebuilder.textoutput;

public interface IndentedTextOutput extends TextOutput
{
	void indent();
	void undent();
}
