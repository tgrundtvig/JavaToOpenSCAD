package org.abstractica.openscadcore.impl.operationsimpl.polygonimpl;

import org.abstractica.openscadcore.intf.polygon.Path;
import org.abstractica.openscadcore.impl.ArgumentCollector;
import org.abstractica.openscadcore.impl.HasArguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathImpl implements Path, HasArguments
{
	private final List<Integer> indexes;

	public PathImpl(Iterable<Integer> indexes)
	{
		List<Integer> tmp = new ArrayList<>();
		for(Integer i : indexes)
		{
			tmp.add(i);
		}
		this.indexes = Collections.unmodifiableList(tmp);
	}

	@Override
	public Iterable<Integer> indexes()
	{
		return indexes;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		for(Integer i : indexes)
		{
			collector.add(i);
		}
	}
}
