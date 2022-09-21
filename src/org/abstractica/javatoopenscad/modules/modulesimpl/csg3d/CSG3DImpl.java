package org.abstractica.javatoopenscad.modules.modulesimpl.csg3d;

import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.CSG3D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3d.G3D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom2d.G3DFrom2D;
import org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom3d.G3DFrom3D;
import org.abstractica.javatoopenscad.coreimpl.core.ModuleFactory;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg3d.g3d.G3DImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg3d.g3dfrom2d.G3DFrom2DImpl;
import org.abstractica.javatoopenscad.modules.modulesimpl.csg3d.g3dfrom3d.G3DFrom3DImpl;
import org.abstractica.javatoopenscad.coreimpl.core.impl.AModuleFactory;

public class CSG3DImpl extends AModuleFactory implements CSG3D
{
	private final G3D g3D;
	private final G3DFrom2D g3DFrom2D;
	private final G3DFrom3D g3DFrom3D;

	public CSG3DImpl(ModuleFactory factory)
	{
		super(factory);
		this.g3D = new G3DImpl(factory);
		this.g3DFrom2D = new G3DFrom2DImpl(factory);
		this.g3DFrom3D = new G3DFrom3DImpl(factory);
	}

	@Override
	public G3D g3D()
	{
		return g3D;
	}

	@Override
	public G3DFrom2D g3DFrom2D()
	{
		return g3DFrom2D;
	}

	@Override
	public G3DFrom3D g3DFrom3D()
	{
		return g3DFrom3D;
	}

}
