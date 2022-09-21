package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.Module2D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.text2d.TextSize;

public interface Text2D
{
	Module2D text(String text, TextFont font, TextSize size, TextAlignment alignment, int resolution);
}
