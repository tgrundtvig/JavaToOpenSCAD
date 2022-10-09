package org.abstractica.javatoopenscad.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.csg.csg3d.*;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.modulesimpl.CSGImpl;

public class CSG3DImpl extends AModuleFactory implements CSG3D
{
	private final Arrange3D arrange3D;
	private final Color3D color3D;
	private final Construct3D construct3D;
	private final Shapes3D shapes3D;
	private final Text3D text3D;

	public CSG3DImpl(CSGImpl csg)
	{
		super(csg);
		arrange3D = new Arrange3DImpl(csg);
		color3D = new Color3DImpl(csg);
		construct3D = new Construct3DImpl(csg);
		shapes3D = new Shapes3DImpl(csg);
		text3D = new Text3DImpl(csg);
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
