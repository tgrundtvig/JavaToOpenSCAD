package org.abstractica.openbuildsystem.generators.sourced.microprocessors;

import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.openbuildsystem.Print3DAdjust;

public class NodeMCUV3
{
	private final CSG csg;
	private final Print3DAdjust adjust;

	public NodeMCUV3(CSG csg, Print3DAdjust adjust)
	{
		this.csg = csg;
		this.adjust = adjust;
	}
}
