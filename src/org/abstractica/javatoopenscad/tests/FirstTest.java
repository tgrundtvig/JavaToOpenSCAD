package org.abstractica.javatoopenscad.tests;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.impl.CodeBuilderImpl;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.textoutput.TextOutput;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.textoutput.impl.StringBuilderTextOutput;
import org.abstractica.javatoopenscad.coreimpl.core.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.modules.modulesintf.CSG;
import org.abstractica.javatoopenscad.modules.modulesintf.CSGMath;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector3D;
import org.abstractica.javatoopenscad.modules.modulesimpl.CSGImpl;

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
