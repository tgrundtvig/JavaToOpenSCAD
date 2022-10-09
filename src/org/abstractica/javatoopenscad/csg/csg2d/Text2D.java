package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAlignment;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextAttributes;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextFont;
import org.abstractica.javatoopenscad.modulesimpl.common.text.TextSize;

public interface Text2D
{
	Module2D text(String text, TextAttributes attributes, int resolution);
	Module2D textLineup(String text, double letterDistance);
	Module2D textLineup(String text,
	                    double letterDistance,
	                    TextFont font,
	                    TextSize size,
	                    int resolution);
	Module2DFrom2D addChars(Module2DFrom2D parent, String text, TextAttributes attributes, int resolution);
}
