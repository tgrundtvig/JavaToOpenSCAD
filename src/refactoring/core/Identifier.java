package refactoring.core;

public interface Identifier
{
	String getFullName();
	String getSimpleName();
	String getSimpeNameWithArguments();
	int getUniqueId();
}
