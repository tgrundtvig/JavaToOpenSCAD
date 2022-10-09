package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.csg.CSGText;
import org.abstractica.javatoopenscad.csg.csg2d.Text2D;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAttributes;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.Text2DModuleImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextLineup2D;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextSize;

public class Text2DImpl extends AModuleFactory implements Text2D
{
	public Text2DImpl(CSGImpl csg)
	{
		super(csg);
	}

	@Override
	public Module2D text(String text, TextAttributes attributes, int resolution)
	{
		return module2D(new Text2DModuleImpl(text, attributes, resolution));
	}

	@Override
	public Module2D textLineup(String text, double letterDistance)
	{
		return textLineup(text, letterDistance, CSGText.defaultFont, CSGText.defaultSize, 16);
	}

	@Override
	public Module2D textLineup(String text,
	                           double letterDistance,
	                           TextFont font,
	                           TextSize size,
	                           int resolution)
	{
		return module2D(new TextLineup2D(text, letterDistance, font, size, resolution));
	}

	@Override
	public Module2DFrom2D addChars(Module2DFrom2D parent, String text, TextAttributes attributes, int resolution)
	{
		for(int i = 0; i < text.length(); ++i)
		{
			char ch = text.charAt(i);
			parent.add(getCSG().csg2D().text2D().text("" + ch, attributes, resolution));
		}
		return parent;
	}
}
