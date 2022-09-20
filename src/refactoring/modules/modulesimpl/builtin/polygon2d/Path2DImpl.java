package refactoring.modules.modulesimpl.builtin.polygon2d;


import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.modules.modulesintf.csg2d.g2d.polygons.Path2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Path2DImpl implements Path2D
{
	private List<Integer> path;

	public Path2DImpl(Iterable<Integer> path)
	{
		ArrayList<Integer> newList = new ArrayList<>();
		for(Integer i : path)
		{
			newList.add(i);
		}
		this.path = Collections.unmodifiableList(newList);
	}

	@Override
	public Iterator<Integer> iterator()
	{
		return this.path.iterator();
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		StringBuilder res = new StringBuilder();
		for(Integer i : path)
		{
			res.append("i");
			res.append(i);
		}
		collector.add("path", res.toString());
	}
}
