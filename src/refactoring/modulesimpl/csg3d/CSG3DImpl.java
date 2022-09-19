package refactoring.modulesimpl.csg3d;

import refactoring.core.*;
import refactoring.modules.PluginModule;
import refactoring.modules.csg3d.CSG3D;
import refactoring.modules.csg3d.g3d.G3D;
import refactoring.modules.csg3d.g3dfrom2d.G3DFrom2D;
import refactoring.modules.csg3d.g3dfrom3d.G3DFrom3D;
import refactoring.modulesimpl.AModuleFactory;
import refactoring.modulesimpl.CSGImpl;

public class CSG3DImpl extends AModuleFactory implements CSG3D
{

	public CSG3DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public G3D g3d()
	{
		return null;
	}

	@Override
	public G3DFrom3D g3DFrom3D()
	{
		return null;
	}

	@Override
	public G3DFrom2D g3DFrom2D()
	{
		return null;
	}

}
