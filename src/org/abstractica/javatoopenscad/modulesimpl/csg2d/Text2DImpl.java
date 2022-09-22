package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.CSGText;
import org.abstractica.javatoopenscad.csg.csg2d.Text2D;
import org.abstractica.javatoopenscad.modulesimpl.common.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextLineup2D;
import org.abstractica.javatoopenscad.modulesimpl.common.TextSize;

public class Text2DImpl extends AModuleFactory implements Text2D
{
	public Text2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module2D text(String text, TextFont font, TextSize size, TextAlignment alignment, int resolution)
	{
		return module2D(new org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.Text2DImpl(text, font, size, alignment, resolution));
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
}
