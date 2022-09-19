package refactoring.tests;

import refactoring.codebuilder.CodeBuilder;
import refactoring.codebuilder.impl.CodeBuilderImpl;
import refactoring.codebuilder.textoutput.TextOutput;
import refactoring.codebuilder.textoutput.impl.StringBuilderTextOutput;
import refactoring.core.BaseModule;
import refactoring.modules.CSG;
import refactoring.modulesimpl.CSGImpl;

import java.util.HashMap;
import java.util.Map;

public class FirstTest
{
	public static void main(String[] args)
	{
		TextOutput out = new StringBuilderTextOutput();
		CodeBuilder cb = new CodeBuilderImpl(   out, "    ",
											"{", "}",
											"(", ", ", ")");

		CSG csg = new CSGImpl();

		BaseModule t = csg.csg2D().g2DFrom2D().translate(10, 20);
		Map<Integer, BaseModule> map = new HashMap<>();
		t.generateCall(cb, map);
		System.out.println(cb);
	}
}
