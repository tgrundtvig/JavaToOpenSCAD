package experiments.codegeneration;

public interface ProgramBlock extends Iterable<Statement>
{
	void add(Statement statement);
}
