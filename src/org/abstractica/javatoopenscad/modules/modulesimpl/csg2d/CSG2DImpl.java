package org.abstractica.javatoopenscad.modules.modulesimpl.csg2d;

import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.CSG2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2d.G2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom2d.G2DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg2d.g2dfrom3d.G2DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2d.G2DImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2dfrom2d.G2DFrom2DImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg2d.g2dfrom3d.G2DFrom3DImpl;

public class CSG2DImpl extends AModuleFactory implements CSG2D
{
	private final G2D g2D;
	private final G2DFrom2D g2DFrom2D;
	private final G2DFrom3D g2DFrom3D;

	public CSG2DImpl(ModuleFactory factory)
	{
		super(factory);
		this.g2D = new G2DImpl(factory);
		this.g2DFrom2D = new G2DFrom2DImpl(factory);
		this.g2DFrom3D = new G2DFrom3DImpl(factory);
	}

	@Override
	public G2D g2D()
	{
		return g2D;
	}

	@Override
	public G2DFrom2D g2DFrom2D()
	{
		return g2DFrom2D;
	}

	@Override
	public G2DFrom3D g2DFrom3D()
	{
		return g2DFrom3D;
	}
}
