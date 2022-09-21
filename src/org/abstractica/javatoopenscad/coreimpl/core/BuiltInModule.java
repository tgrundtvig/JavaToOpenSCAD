package org.abstractica.javatoopenscad.coreimpl.core;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;

public interface BuiltInModule extends PluginModule
{
	void getCallHeader(CodeBuilder cb);
}
