package refactoring.core;

import refactoring.codebuilder.CodeBuilder;
import refactoring.codebuilder.blockbuilder.BlockBuilder;
import refactoring.modules.CSG;
import refactoring.modules.plugininterfaces.*;
import refactoring.modules.PluginModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseModuleImpl implements Module2DFrom2D, Module2DFrom3D, Module3DFrom2D, Module3DFrom3D
{
	private final CSG csg;
	private final Identifier id;
	private final PluginModule plugin;
	private ArrayList<BaseModule> children;

	public BaseModuleImpl(CSG csg, Identifier identifier, PluginModule plugin)
	{
		this.csg = csg;
		this.id = identifier;
		this.plugin = plugin;
		this.children = null;
	}

	@Override
	public Identifier getIdentifier()
	{
		return id;
	}

	@Override
	public void generateCall(CodeBuilder cb, Map<Integer, BaseModule> usedModules)
	{
		cb.print("// ");
		cb.println(id.getSimpeNameWithArguments());
		if(plugin instanceof BuiltInModule)
		{
			BuiltInModule builtIn = (BuiltInModule) plugin;
			cb.print(builtIn.getCallHeader());
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
			for(BaseModule child : children)
			{
				child.generateCall(childBlock, usedModules);
			}
			childBlock.endBlock();
		}

	}

	@Override
	public void generateModule(CodeBuilder cb, Map<Integer, BaseModule> usedModules)
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
		BaseModule geometry = generateGeometry();
		geometry.generateCall(bodyBlock,usedModules);
		bodyBlock.endBlock();
	}

	private BaseModule generateGeometry()
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
					"not have beeen added to used modules.");
		}
		else
		{
			throw new RuntimeException("Unknown PluginModule type: " + plugin.getClass().getName());
		}
	}

	@Override
	public void add(Module2D child)
	{
		doAdd(child);
	}

	@Override
	public void add(Module3D child)
	{
		doAdd(child);
	}

	private void doAdd(BaseModule child)
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
		for(BaseModule child : children)
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
		for(BaseModule child : children)
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
}
