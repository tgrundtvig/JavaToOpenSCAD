package refactoring.core;

import refactoring.codebuilder.CodeBuilder;

import java.util.Map;

public interface BaseModule
{
	Identifier getIdentifier();
	//Code generation
	void generateCall(CodeBuilder cb, Map<Integer, BaseModule> usedModules);
	void generateModule(CodeBuilder cb, Map<Integer, BaseModule> usedModules);
}
