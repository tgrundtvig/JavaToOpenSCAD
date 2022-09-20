package refactoring.coreimpl.core;

import refactoring.coreimpl.codebuilder.CodeBuilder;

import java.util.Map;

public interface OpenSCADModule
{
	Identifier getIdentifier();
	//Code generation
	void generateCall(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules);
	void generateModule(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules);
}
