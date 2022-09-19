package refactoring.codebuilder.textoutput;

public interface IndentedTextOutput extends TextOutput
{
	void indent();
	void undent();
}
