package experiments.codegeneration;

public interface NodeEntity extends ProgramEntity, Iterable<ProgramEntity>
{
	void add(ProgramEntity child);
}
