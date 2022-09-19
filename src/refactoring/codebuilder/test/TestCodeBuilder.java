package refactoring.codebuilder.test;

import refactoring.codebuilder.CodeBuilder;

import refactoring.codebuilder.blockbuilder.BlockBuilder;
import refactoring.codebuilder.impl.CodeBuilderImpl;
import refactoring.codebuilder.textoutput.TextOutput;
import refactoring.codebuilder.textoutput.impl.StringBuilderTextOutput;

public class TestCodeBuilder
{
	public static void main(String[] args)
	{
		TextOutput out = new StringBuilderTextOutput();
		CodeBuilder cb = new CodeBuilderImpl(out,   "    ",
													"{", "}",
													"(", ", ", ")");

		cb.print("Foo");
		cb.list().add("bar = 0").add("baz = 42").end();
		BlockBuilder bb = cb.block();
		bb.println("Statement1;");
		bb.println("Statement2;");
		bb.println("Statement3;");
		bb.print("while(true)");
		BlockBuilder bb2 = bb.block();
		bb2.println("Statement4;");
		bb2.println("Statement5;");
		bb2.endBlock();
		bb.println("Statement6");
		bb.println("Statement7");
		bb.endBlock();

		System.out.println(cb.toString());
	}
}
