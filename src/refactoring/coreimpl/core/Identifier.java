package refactoring.coreimpl.core;

public interface Identifier
{
	String getFullName();
	String getSimpleName();
	String getSimpeNameWithArguments();
	int getUniqueId();
}
