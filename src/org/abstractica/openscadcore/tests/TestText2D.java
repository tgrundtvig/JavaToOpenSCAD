package org.abstractica.openscadcore.tests;


import org.abstractica.openscadcore.impl.OpenSCADCoreImpl;
import org.abstractica.openscadcore.impl.operationsimpl.identifier.AllStrings;

import org.abstractica.openscadcore.intf.Geometry2D;
import org.abstractica.openscadcore.intf.OpenSCADCore;
import org.abstractica.openscadcore.intf.text.TextAlignment;
import org.abstractica.openscadcore.intf.text.TextAttributes;
import org.abstractica.openscadcore.intf.text.TextFont;
import org.abstractica.openscadcore.intf.text.TextSize;


import java.io.IOException;


public class TestText2D
{
	public static void main(String[] args) throws IOException
	{
		OpenSCADCore os = new OpenSCADCoreImpl(null);
		TextFont font = os.textFont("Consolas", "Regular","en", "latin");
		TextSize size = os.textSize(10/0.7, 1);
		TextAlignment alignment = os.textAlignment(TextAlignment.Direction.LEFT_TO_RIGHT,
				TextAlignment.Horizontal.CENTER, TextAlignment.Vertical.BASELINE);
		TextAttributes attr = os.textAttributes(font, size, alignment);
		Geometry2D geometry = os.text("Hello OpenSCAD!", attr, 64);

		os.generateOpenSCADFile("OpenSCAD/output.scad", geometry);
		System.out.println(AllStrings.listAllStrings());
	}
}
