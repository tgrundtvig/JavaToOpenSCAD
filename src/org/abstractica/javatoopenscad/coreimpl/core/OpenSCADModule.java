package org.abstractica.javatoopenscad.coreimpl.core;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;

import java.util.Map;

public interface OpenSCADModule
{
	Identifier getIdentifier();
	void debugMark();
	//Code generation
	void generateCall(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules);
	void generateModule(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules);

}
