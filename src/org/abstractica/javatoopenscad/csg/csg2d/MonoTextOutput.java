package org.abstractica.javatoopenscad.csg.csg2d;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.textoutput.TextOutput;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;

public interface MonoTextOutput extends TextOutput
{
	Module2D buildModule();
}
