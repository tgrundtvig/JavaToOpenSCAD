package refactoring.core;

import refactoring.modules.plugininterfaces.ArgumentCollector;

public interface HasArguments
{
	void getArguments(ArgumentCollector collector);
}
