package org.abstractica.javatoopenscad.scad.module.impl;

import java.util.*;

public class AllStrings
{
	private final static Map<String, Integer> allStrings = new HashMap<>();
	private static int stringCount = 0;

	public static int id(String str)
	{
		Integer id = allStrings.get(str);
		if(id == null)
		{
			allStrings.put(str, ++stringCount);
			id = stringCount;
		}
		return id;
	}


	///////////////////////////////////////////////////////////////////////////////////////////
	// Debugging tools:
	//////////////////////////////////////////////////////////////////////////////////////////

	public static String listAllStrings()
	{
		StringBuilder res = new StringBuilder();
		List<UniqueString> uniqueStrings = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : allStrings.entrySet())
		{
			UniqueString ustr = new UniqueString(entry.getKey(), entry.getValue());
			uniqueStrings.add(ustr);
		}
		Collections.sort(uniqueStrings);
		for(UniqueString uniqueStr : uniqueStrings)
		{
			res.append(uniqueStr.id);
			res.append(": \"");
			res.append(uniqueStr.str);
			res.append("\"");
			res.append(System.lineSeparator());
		}
		return res.toString();
	}

	private static class UniqueString implements Comparable<UniqueString>
	{
		public final String str;
		public final int id;

		private UniqueString(String str, int id)
		{
			this.str = str;
			this.id = id;
		}

		@Override
		public int compareTo(UniqueString o)
		{
			return id - o.id;
		}
	}
}
