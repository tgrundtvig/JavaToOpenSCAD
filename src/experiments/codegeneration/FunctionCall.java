package experiments.codegeneration;

public interface FunctionCall extends Expression, Iterable<Argument>
{
	void add(Argument argument);
}
