package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.csg.csg3d.*;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;

public class CSG3DImpl extends AModuleFactory implements CSG3D
{
	private final Arrange3D arrange3D;
	private final Color3D color3D;
	private final Construct3D construct3D;
	private final Shapes3D shapes3D;
	private final Text3D text3D;

	public CSG3DImpl(ModuleFactory factory)
	{
		super(factory);
		arrange3D = new Arrange3DImpl(factory);
		color3D = new Color3DImpl(factory);
		construct3D = new Construct3DImpl(factory);
		shapes3D = new Shapes3DImpl(factory);
		text3D = new Text3DImpl(factory);
	}


	@Override
	public Arrange3D arrange3D()
	{
		return arrange3D;
	}

	@Override
	public Color3D color3D()
	{
		return color3D;
	}

	@Override
	public Construct3D construct3D()
	{
		return construct3D;
	}

	@Override
	public Shapes3D shapes3D()
	{
		return shapes3D;
	}

	@Override
	public Text3D text3D()
	{
		return text3D;
	}
}
