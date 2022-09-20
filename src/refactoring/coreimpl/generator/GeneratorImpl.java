package refactoring.coreimpl.generator;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.OpenSCADModule;

import java.util.HashMap;
import java.util.Map;

public class GeneratorImpl implements Generator
{
	@Override
	public void generate(CodeBuilder cb, OpenSCADModule module)
	{
		Map<Integer, OpenSCADModule> usedModules = new HashMap<>();
		Map<Integer, OpenSCADModule> createdModules = new HashMap<>();
		module.generateCall(cb, usedModules);
		while(!usedModules.isEmpty())
		{
			Map<Integer, OpenSCADModule> newUsedModules = new HashMap<>();
			for(OpenSCADModule mod : usedModules.values())
			{
				mod.generateModule(cb, newUsedModules);
				createdModules.put(mod.getIdentifier().getUniqueId(), mod);
			}
			usedModules = new HashMap<>();
			for(OpenSCADModule mod : newUsedModules.values())
			{
				if(!createdModules.containsKey(mod.getIdentifier().getUniqueId()))
				{
					usedModules.put(mod.getIdentifier().getUniqueId(), mod);
				}
			}
		}
	}
}
