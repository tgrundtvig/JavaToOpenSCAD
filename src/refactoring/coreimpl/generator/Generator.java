package refactoring.coreimpl.generator;


import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.OpenSCADModule;

public interface Generator
{
	void generate(CodeBuilder cb, OpenSCADModule module);
}
