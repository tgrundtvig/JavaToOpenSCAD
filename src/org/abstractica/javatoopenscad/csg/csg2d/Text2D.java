package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.modulesimpl.common.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.common.TextSize;

public interface Text2D
{
	Module2D text(String text, TextFont font, TextSize size, TextAlignment alignment, int resolution);
	Module2D textLineup(String text, double letterDistance);
	Module2D textLineup(String text,
	                    double letterDistance,
	                    TextFont font,
	                    TextSize size,
	                    int resolution);
}
