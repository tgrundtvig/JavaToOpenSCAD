package refactoring.coreimpl.fileoutput;

import refactoring.coreimpl.codebuilder.impl.CodeBuilderImpl;
import refactoring.coreimpl.codebuilder.textoutput.TextOutput;
import refactoring.coreimpl.codebuilder.textoutput.impl.StringBuilderTextOutput;
import refactoring.coreimpl.core.OpenSCADModule;
import refactoring.coreimpl.core.impl.AllStrings;
import refactoring.coreimpl.generator.Generator;
import refactoring.coreimpl.generator.GeneratorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenSCADFileOutput
{
	public static void generateOutput(OpenSCADModule module) throws IOException
	{
		generateFile("OpenSCAD/output.scad", module);
	}

	public static void generateFile(String fileName, OpenSCADModule module) throws IOException
	{
		Path path = Paths.get(fileName);
		generateFile(path, module);
	}

	public static void generateFile(Path path, OpenSCADModule module) throws IOException
	{
		TextOutput out = new StringBuilderTextOutput();
		CodeBuilderImpl cb = new CodeBuilderImpl(   out,
													"    ",
													"{", "}",
													"(", ", ", ")"  );
		Generator generator = new GeneratorImpl();
		generator.generate(cb,module);
		Path parentDir = path.getParent();
		if (!Files.exists(parentDir))
			Files.createDirectories(parentDir);
		Files.writeString(path, cb.toString());
		System.out.println(AllStrings.listAllStrings());
	}

}
