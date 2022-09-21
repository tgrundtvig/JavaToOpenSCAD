package org.abstractica.javatoopenscad.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.csg.csg2d.*;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;

public class CSG2DImpl extends AModuleFactory implements CSG2D
{
	private final Arrange2D arrange2D;
	private final Color2D color2D;
	private final Construct2D construct2D;
	private final Shapes2D shapes2D;
	private final Text2D text2D;

	public CSG2DImpl(ModuleFactory factory)
	{
		super(factory);
		arrange2D = new Arrange2DImpl(factory);
		color2D = new Color2DImpl(factory);
		construct2D = new Construc2DImpl(factory);
		shapes2D = new Shapes2DImpl(factory);
		text2D = new Text2DImpl(factory);
	}

	@Override
	public Arrange2D arrange2D()
	{
		return arrange2D;
	}

	@Override
	public Color2D color2D()
	{
		return color2D;
	}

	@Override
	public Construct2D construct2D()
	{
		return construct2D;
	}

	@Override
	public Shapes2D shapes2D()
	{
		return shapes2D;
	}

	@Override
	public Text2D text2D()
	{
		return text2D;
	}
}
