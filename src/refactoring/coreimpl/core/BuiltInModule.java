package refactoring.coreimpl.core;

import refactoring.coreimpl.codebuilder.CodeBuilder;

public interface BuiltInModule extends PluginModule
{
	void getCallHeader(CodeBuilder cb);
}
