package refactoring.modules.modulesimpl;

import refactoring.coreimpl.core.*;
import refactoring.coreimpl.core.impl.IdentifierImpl;
import refactoring.coreimpl.core.impl.OpenSCADModuleImpl;
import refactoring.modules.modulesintf.CSG;
import refactoring.modules.modulesintf.csg2d.CSG2D;
import refactoring.modules.modulesintf.csg3d.CSG3D;
import refactoring.modules.modulesimpl.plugininterfaces.*;
import refactoring.modules.modulesimpl.csg2d.CSG2DImpl;
import refactoring.modules.modulesimpl.csg3d.CSG3DImpl;

import java.util.HashMap;
import java.util.Map;

public class CSGImpl implements CSG, ModuleFactory
{
	private final Map<Integer, OpenSCADModuleImpl> map;
	private final CSG2D csg2D;
	private final CSG3D csg3D;

	public CSGImpl()
	{
		this.map = new HashMap<>();
		this.csg2D = new CSG2DImpl(this);
		this.csg3D = new CSG3DImpl(this);
	}

	@Override
	public CSG2D csg2D()
	{
		return csg2D;
	}

	@Override
	public CSG3D csg3D()
	{
		return csg3D;
	}

	@Override
	public Module2D module2D(BuiltInModule impl)
	{
		return getUniqueBaseModule(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(BuiltInModule impl)
	{
		return getBaseModule(impl).asModule2DFrom2D();
	}

	@Override
	public Module2DFrom3D module2DFrom3D(BuiltInModule impl)
	{
		return getBaseModule(impl).asModule2DFrom3D();
	}

	@Override
	public Module3D module3D(BuiltInModule impl)
	{
		return getUniqueBaseModule(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(BuiltInModule impl)
	{
		return getBaseModule(impl).asModule3DFrom2D();
	}

	@Override
	public Module3DFrom3D module3DFrom3D(BuiltInModule impl)
	{
		return getBaseModule(impl).asModule3DFrom3D();
	}

	@Override
	public Module2D module2D(Module2DImpl impl)
	{
		return getUniqueBaseModule(impl);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(Module2DFrom2DImpl impl)
	{
		return getBaseModule(impl).asModule2DFrom2D();
	}

	@Override
	public Module2DFrom3D module2DFrom3D(Module2DFrom3DImpl impl)
	{
		return getBaseModule(impl).asModule2DFrom3D();
	}

	@Override
	public Module3D module3D(Module3DImpl impl)
	{
		return getUniqueBaseModule(impl);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(Module3DFrom2DImpl impl)
	{
		return getBaseModule(impl).asModule3DFrom2D();
	}

	@Override
	public Module3DFrom3D module3DFrom3D(Module3DFrom3DImpl impl)
	{
		return getBaseModule(impl).asModule3DFrom3D();
	}

	private OpenSCADModuleImpl getBaseModule(PluginModule plugInModule)
	{
		Identifier id = new IdentifierImpl(plugInModule);
		return new OpenSCADModuleImpl(this, id, plugInModule);
	}

	private OpenSCADModuleImpl getUniqueBaseModule(PluginModule plugInModule)
	{
		Identifier id = new IdentifierImpl(plugInModule);
		OpenSCADModuleImpl res = map.get(id.getUniqueId());
		if(res == null)
		{
			res = new OpenSCADModuleImpl(this, id, plugInModule);
			map.put(id.getUniqueId(), res);
		}
		return res;
	}
}
