package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.csg.csg2d.Text2D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextSize;

public class Text2DImpl extends AModuleFactory implements Text2D
{
	public Text2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module2D text(String text, TextFont font, TextSize size, TextAlignment alignment, int resolution)
	{
		return module2D(new org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.Text2D(text, font, size, alignment, resolution));
	}
}
