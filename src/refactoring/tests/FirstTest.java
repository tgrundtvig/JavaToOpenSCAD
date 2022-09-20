package refactoring.tests;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.codebuilder.impl.CodeBuilderImpl;
import refactoring.coreimpl.codebuilder.textoutput.TextOutput;
import refactoring.coreimpl.codebuilder.textoutput.impl.StringBuilderTextOutput;
import refactoring.coreimpl.core.OpenSCADModule;
import refactoring.coreimpl.core.Module2DFrom2D;
import refactoring.modules.modulesintf.CSGMath;
import refactoring.modules.modulesintf.math.Vector3D;
import refactoring.modules.modulesintf.CSG;
import refactoring.modules.modulesimpl.CSGImpl;

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
		Vector3D v = CSGMath.VECTOR3D_ORIGO;
		Module2DFrom2D t = csg.csg2D().g2DFrom2D().translate2D(10, 20);
		Module2DFrom2D s = csg.csg2D().g2DFrom2D().translate2D(10, 20);
		t.add(s);
		Map<Integer, OpenSCADModule> map = new HashMap<>();
		t.generateCall(cb, map);
		System.out.println(cb);
	}
}
