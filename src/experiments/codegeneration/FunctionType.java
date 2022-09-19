package experiments.codegeneration;

import experiments.codegeneration.valuesandtypes.Type;

public interface FunctionType extends Type, Iterable<Parameter>
{
	Type getReturnType();
}
