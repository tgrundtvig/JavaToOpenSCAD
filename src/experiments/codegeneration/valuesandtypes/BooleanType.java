package experiments.codegeneration.valuesandtypes;

public class BooleanType implements Type, Value
{
	private final boolean value;

	public BooleanType(boolean value)
	{
		this.value = value;
	}

	@Override
	public Type getType()
	{
		return this;
	}

	@Override
	public String valueAsString()
	{
		return Boolean.toString(value);
	}

	@Override
	public String typeAsString()
	{
		return "Boolean";
	}
}
