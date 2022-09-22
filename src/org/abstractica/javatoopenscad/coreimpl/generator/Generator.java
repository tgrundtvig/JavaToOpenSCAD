package org.abstractica.javatoopenscad.coreimpl.generator;


import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;

public interface Generator
{
	void generate(CodeBuilder cb, OpenSCADModule module);
}
