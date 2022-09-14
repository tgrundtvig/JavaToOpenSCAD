package org.abstractica.javatoopenscad.scad.impl.impl2d;

import org.abstractica.javatoopenscad.scad.scad2d.Path2D;

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
}
