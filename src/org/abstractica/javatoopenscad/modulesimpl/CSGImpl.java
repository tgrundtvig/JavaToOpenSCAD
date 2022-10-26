package org.abstractica.javatoopenscad.modulesimpl;

import org.abstractica.javatoopenscad.coreimpl.core.*;
import org.abstractica.javatoopenscad.coreimpl.core.impl.IdentifierImpl;
import org.abstractica.javatoopenscad.coreimpl.core.impl.OpenSCADModuleImpl;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.CSG2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module2DFrom3D;
import org.abstractica.javatoopenscad.csg.csg3d.CSG3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.CSG2DImpl;
import org.abstractica.javatoopenscad.modulesimpl.csg3d.CSG3DImpl;
import org.abstractica.javatoopenscad.plugininterfaces.*;

import java.util.HashMap;
import java.util.Map;

public class CSGImpl implements CSG, ModuleFactory
{
	private static int instanceID = 0;
	private final Map<Integer, PluginModule> uniquePlugins;
	private final Map<Integer, OpenSCADModuleImpl> uniqueModules;
	private final CSG2D csg2D;
	private final CSG3D csg3D;

	public CSGImpl()
	{
		this.uniquePlugins = new HashMap<>();
		this.uniqueModules = new HashMap<>();
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
		//This is to prevent the same Mxxx module name for two different instances.
		Identifier id = new IdentifierImpl(plugInModule, instanceID++);
		plugInModule = uniquePlugin(id, plugInModule);
		return new OpenSCADModuleImpl(this, id, plugInModule);
	}

	private PluginModule uniquePlugin(Identifier id, PluginModule plugInModule)
	{
		PluginModule res = uniquePlugins.get(id.getUniqueId());
		if(res == null)
		{
			res = plugInModule;
			uniquePlugins.put(id.getUniqueId(), res);
		}
		return res;
	}

	private OpenSCADModuleImpl getUniqueBaseModule(PluginModule plugInModule)
	{
		Identifier id = new IdentifierImpl(plugInModule);
		plugInModule = uniquePlugin(id, plugInModule);
		OpenSCADModuleImpl res = uniqueModules.get(id.getUniqueId());
		if(res == null)
		{
			res = new OpenSCADModuleImpl(this, id, plugInModule);
			uniqueModules.put(id.getUniqueId(), res);
		}
		return res;
	}
}
