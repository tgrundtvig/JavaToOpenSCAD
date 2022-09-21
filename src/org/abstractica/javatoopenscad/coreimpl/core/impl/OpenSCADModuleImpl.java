package org.abstractica.javatoopenscad.coreimpl.core.impl;

import org.abstractica.javatoopenscad.coreimpl.codebuilder.CodeBuilder;
import org.abstractica.javatoopenscad.coreimpl.codebuilder.blockbuilder.BlockBuilder;
import org.abstractica.javatoopenscad.coreimpl.core.*;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.plugininterfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenSCADModuleImpl implements Module2D, Module3D
{
	private final M2DFrom2DImpl asM2DFrom2D;
	private final M2DFrom3DImpl asM2DFrom3D;
	private final M3DFrom2DImpl asM3DFrom2D;
	private final M3DFrom3DImpl asM3DFrom3D;
	private final CSG csg;
	private final Identifier id;
	private final PluginModule plugin;
	private ArrayList<OpenSCADModule> children;

	public OpenSCADModuleImpl(CSG csg, Identifier identifier, PluginModule plugin)
	{
		this.csg = csg;
		this.id = identifier;
		this.plugin = plugin;
		this.children = null;
		asM2DFrom2D = new M2DFrom2DImpl();
		asM2DFrom3D = new M2DFrom3DImpl();
		asM3DFrom2D = new M3DFrom2DImpl();
		asM3DFrom3D = new M3DFrom3DImpl();
	}

	@Override
	public Identifier getIdentifier()
	{
		return id;
	}

	@Override
	public void generateCall(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules)
	{
		cb.print("// ");
		cb.println(id.getSimpeNameWithArguments());
		if(plugin instanceof BuiltInModule)
		{
			BuiltInModule builtIn = (BuiltInModule) plugin;
			builtIn.getCallHeader(cb);
		}
		else
		{
			cb.print("M");
			cb.print(Integer.toString(id.getUniqueId()));
			cb.list().end(); // ()
			usedModules.put(id.getUniqueId(), this);
		}
		if(children == null)
		{
			cb.println(";");
		}
		else
		{
			BlockBuilder childBlock = cb.block();
			for(OpenSCADModule child : children)
			{
				child.generateCall(childBlock, usedModules);
			}
			childBlock.endBlock();
		}

	}

	@Override
	public void generateModule(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules)
	{
		if(plugin instanceof BuiltInModule)
		{
			throw new RuntimeException("This should never happen, since this module should " +
										"not have beeen added to used modules.");
		}
		cb.println();
		cb.print("// ");
		cb.println(id.getSimpeNameWithArguments());
		cb.print("module M");
		cb.print(Integer.toString(id.getUniqueId()));
		cb.list().end();
		BlockBuilder bodyBlock = cb.block();
		OpenSCADModule geometry = generateGeometry();
		geometry.generateCall(bodyBlock,usedModules);
		bodyBlock.endBlock();
	}

	private OpenSCADModule generateGeometry()
	{
		if(plugin instanceof Module2DImpl)
		{
			return ((Module2DImpl) plugin).buildGeometry(csg);
		}
		else if(plugin instanceof Module2DFrom2DImpl)
		{
			return ((Module2DFrom2DImpl) plugin).buildGeometry(csg, childrenAsModule2D());
		}
		else if(plugin instanceof Module2DFrom3DImpl)
		{
			return ((Module2DFrom3DImpl) plugin).buildGeometry(csg, childrenAsModule3D());
		}
		if(plugin instanceof Module3DImpl)
		{
			return ((Module3DImpl) plugin).buildGeometry(csg);
		}
		else if(plugin instanceof Module3DFrom2DImpl)
		{
			return ((Module3DFrom2DImpl) plugin).buildGeometry(csg, childrenAsModule2D());
		}
		else if(plugin instanceof Module3DFrom3DImpl)
		{
			return ((Module3DFrom3DImpl) plugin).buildGeometry(csg, childrenAsModule3D());
		}
		else if(plugin instanceof BuiltInModule)
		{
			throw new RuntimeException("This should never happen, since this module should " +
					"not have been added to used modules.");
		}
		else
		{
			throw new RuntimeException("Unknown PluginModule type: " + plugin.getClass().getName());
		}
	}

	private void doAdd(OpenSCADModule child)
	{
		if(children == null)
		{
			children = new ArrayList<>();
		}
		children.add(child);
	}

	private List<Module2D> childrenAsModule2D()
	{
		ArrayList<Module2D> childrenAsModule2D = new ArrayList<>();
		for(OpenSCADModule child : children)
		{
			if(!(child instanceof Module2D))
			{
				throw new RuntimeException("Wrong type of child has been added! " +
						"this should not be possible if " +
						" the interfaces are respected!");
			}
			childrenAsModule2D.add((Module2D) child);
		}
		return childrenAsModule2D;
	}

	private List<Module3D> childrenAsModule3D()
	{
		ArrayList<Module3D> childrenAsModule3D = new ArrayList<>();
		for(OpenSCADModule child : children)
		{
			if(!(child instanceof Module3D))
			{
				throw new RuntimeException("Wrong type of child has been added! " +
						"this should not be possible if " +
						" the interfaces are respected!");
			}
			childrenAsModule3D.add((Module3D) child);
		}
		return childrenAsModule3D;
	}

	public Module2DFrom2D asModule2DFrom2D()
	{
		return asM2DFrom2D;
	}

	public Module2DFrom3D asModule2DFrom3D()
	{
		return asM2DFrom3D;
	}

	public Module3DFrom2D asModule3DFrom2D()
	{
		return asM3DFrom2D;
	}

	public Module3DFrom3D asModule3DFrom3D()
	{
		return asM3DFrom3D;
	}

	private abstract class ModuleXFromX implements OpenSCADModule
	{

		@Override
		public Identifier getIdentifier()
		{
			return OpenSCADModuleImpl.this.getIdentifier();
		}

		@Override
		public void generateCall(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules)
		{
			OpenSCADModuleImpl.this.generateCall(cb, usedModules);
		}

		@Override
		public void generateModule(CodeBuilder cb, Map<Integer, OpenSCADModule> usedModules)
		{
			OpenSCADModuleImpl.this.generateModule(cb, usedModules);
		}
	}


	private class M2DFrom2DImpl extends ModuleXFromX implements Module2DFrom2D
	{
		@Override
		public Module2DFrom2D add(Module2D child)
		{
			doAdd(child);
			return this;
		}
	}

	private class M2DFrom3DImpl extends ModuleXFromX implements Module2DFrom3D
	{

		@Override
		public Module2DFrom3D add(Module3D child)
		{
			doAdd(child);
			return this;
		}
	}

	private class M3DFrom2DImpl extends ModuleXFromX implements Module3DFrom2D
	{
		@Override
		public Module3DFrom2D add(Module2D child)
		{
			doAdd(child);
			return this;
		}
	}

	private class M3DFrom3DImpl extends ModuleXFromX implements Module3DFrom3D
	{
		@Override
		public Module3DFrom3D add(Module3D child)
		{
			doAdd(child);
			return this;
		}
	}
}
