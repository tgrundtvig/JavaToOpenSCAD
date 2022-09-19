package experiments.codegeneration;

public interface Function extends Identifier
{
	FunctionType getType();
	FunctionCall createFunctionCall();
}
