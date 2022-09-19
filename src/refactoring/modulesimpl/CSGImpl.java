package refactoring.modulesimpl;

import refactoring.core.*;
import refactoring.modules.CSG;
import refactoring.modules.PluginModule;
import refactoring.modules.csg2d.CSG2D;
import refactoring.modules.csg3d.CSG3D;
import refactoring.modulesimpl.csg2d.CSG2DImpl;
import refactoring.modulesimpl.csg3d.CSG3DImpl;

import java.util.HashMap;
import java.util.Map;

public class CSGImpl implements CSG, ModuleFactory
{
	private final Map<Integer, BaseModuleImpl> map;
	private final CSG2D csg2D;
	private final CSG3D csg3D;

	public CSGImpl()
	{
		this.map = new HashMap<>();
		csg2D = new CSG2DImpl(this);
		csg3D = new CSG3DImpl(this);
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
	public Module2D module2D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	@Override
	public Module2DFrom2D module2DFrom2D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	@Override
	public Module2DFrom3D module2DFrom3D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	@Override
	public Module3D module3D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	@Override
	public Module3DFrom2D module3DFrom2D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	@Override
	public Module3DFrom3D module3DFrom3D(PluginModule plugInModule)
	{
		return getUniqueBaseModule(plugInModule);
	}

	private BaseModuleImpl getUniqueBaseModule(PluginModule plugInModule)
	{
		Identifier id = new ArgumentCollectorImpl(plugInModule);
		BaseModuleImpl res = map.get(id.getUniqueId());
		if(res == null)
		{
			res = new BaseModuleImpl(this, id, plugInModule);
			map.put(id.getUniqueId(), res);
		}
		return res;
	}
}
