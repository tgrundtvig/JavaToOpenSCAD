package refactoring.coreimpl.core;

public interface HasArguments
{
	void getArguments(ArgumentCollector collector);
	default String getSimpleName()
	{
		return getClass().getSimpleName();
	}
}
