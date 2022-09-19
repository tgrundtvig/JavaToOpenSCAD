package refactoring.modules.plugininterfaces;

import refactoring.core.BaseModule;
import refactoring.core.HasArguments;

public interface ArgumentCollector
{
	void add(String name, boolean b);
	void add(String name, int i);
	void add(String name, double d);
	void add(String name, String str);
	void add(String name, BaseModule module);
	void add(String name, HasArguments element);
}
