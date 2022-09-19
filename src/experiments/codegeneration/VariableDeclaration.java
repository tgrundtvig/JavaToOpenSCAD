package experiments.codegeneration;

public interface VariableDeclaration extends Statement
{
	VariableName getName();
	TypeName getTypeName();
}
