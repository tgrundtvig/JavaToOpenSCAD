package experiments.codegeneration;

import experiments.codegeneration.valuesandtypes.Type;

public interface Factory
{
	ProgramBlock getProgramBlock();
	TypeName getTypeName(String typeName);
	FunctionType getFunctionTypeBuilder(Type returnType);
	Function declareFunction(String functionName, FunctionType type);
	VariableDeclaration createVariableDeclaration(String name, TypeName type);
	VariableAssignment createVariableAssignment(VariableName name, Expression expression);
}
