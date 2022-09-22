package org.abstractica.javatoopenscad.coreimpl.core.impl;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.OpenSCADModule;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.csg2d.Polar2D;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Polar3D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;
import org.abstractica.javatoopenscad.coreimpl.core.HasArguments;
import org.abstractica.javatoopenscad.coreimpl.core.Identifier;
import org.abstractica.javatoopenscad.modulesimpl.csg2d.construct2d.polygon2d.Polygon2D;
import org.abstractica.javatoopenscad.csg.csg2d.Path2D;

import java.util.List;
import java.util.Locale;

public class IdentifierImpl implements ArgumentCollector, Identifier
{
	private StringBuilder uniqueId;
	private StringBuilder clearText;
	private final String simpleName;
	private final String fullName;
	private final String nameWithArguments;
	private final int id;
	private boolean first;

	public IdentifierImpl(HasArguments item)
	{
		this.simpleName = item.getSimpleName();
		this.fullName = item.getClass().getName();
		this.uniqueId = new StringBuilder();
		this.clearText = new StringBuilder();
		first = true;
		uniqueId.append(AllStrings.id(fullName));
		clearText.append(simpleName);
		clearText.append("(");
		item.getArguments(this);
		clearText.append(")");
		nameWithArguments = clearText.toString();
		id = AllStrings.id(uniqueId.toString());
		uniqueId = null;
		clearText = null;
	}

	@Override
	public void add(String name, boolean b)
	{
		uniqueId.append("b").append(b ? 1 : 0);
		prefix(name);
		clearText.append(b);
	}

	@Override
	public void add(String name, int i)
	{
		uniqueId.append("i").append(AllStrings.id(Integer.toString(i)));
		prefix(name);
		clearText.append(i);
	}

	@Override
	public void add(String name, double d)
	{
		if(Math.abs(d) < 0.0000000001) d = 0.0;
		String doubleString = String.format(Locale.ENGLISH,"%.10f", d);
		uniqueId.append("d").append(AllStrings.id(doubleString));
		prefix(name);
		clearText.append(d(d));
	}

	@Override
	public void add(String name, String str)
	{
		uniqueId.append("s").append(AllStrings.id(str));
		prefix(name);
		clearText.append("\"");
		clearText.append(str);
		clearText.append("\"");
	}

	@Override
	public void add(String name, Angle angle)
	{
		add(name, (HasArguments) angle);
	}

	@Override
	public void add(String name, Vector2D v2D)
	{
		add(name, (HasArguments) v2D);
	}

	@Override
	public void add(String name, Polar2D p2D)
	{
		add(name, (HasArguments) p2D);
	}

	@Override
	public void add(String name, Vector3D v3D)
	{
		add(name, (HasArguments) v3D);
	}

	@Override
	public void add(String name, Polar3D p3D)
	{
		add(name, (HasArguments) p3D);
	}

	@Override
	public void add(String name, Polygon2D polygon)
	{
		List<Vector2D> vertices = polygon.getVertices();

		for(int i = 0; i < vertices.size(); ++i)
		{
			Vector2D v = vertices.get(i);
			add("P" + i, v);
		}

		List<Path2D> paths = polygon.getPaths();
		if(paths != null)
		{
			for(int i = 0; i < paths.size(); ++i)
			{
				Path2D path = paths.get(i);
				add("path"+i, path);
			}
		}
	}

	@Override
	public void add(String name, OpenSCADModule module)
	{
		uniqueId.append("m").append(module.getIdentifier().getUniqueId());
		prefix(name);
		clearText.append(module.getIdentifier().getSimpleName());
	}


	@Override
	public void add(String name, HasArguments element)
	{
		Identifier identifier = new IdentifierImpl(element);
		uniqueId.append("a").append(identifier.getUniqueId());
		prefix(name);
		clearText.append(identifier.getSimpeNameWithArguments());
	}

	@Override
	public void add(String name, List<HasArguments> elements)
	{
		StringBuilder res = new StringBuilder();
		for(HasArguments element : elements)
		{
			Identifier elemId = new IdentifierImpl(element);
			res.append('a');
			res.append(elemId.getUniqueId());
		}
		int newId = AllStrings.id(res.toString());
		uniqueId.append("a").append(newId);
		prefix(name);
		clearText.append("ListOfArguments");
	}

	@Override
	public String getFullName()
	{
		return fullName;
	}

	@Override
	public String getSimpleName()
	{
		return simpleName;
	}

	@Override
	public String getSimpeNameWithArguments()
	{
		return nameWithArguments;
	}

	public int getUniqueId()
	{
		return id;
	}

	public String getClearText()
	{
		return clearText.toString();
	}

	private String d(double d)
	{
		return String.format(Locale.ENGLISH, "%.4f", d);
	}

	private void prefix(String name)
	{
		if (first)
		{
			first = false;
		} else
		{
			clearText.append(", ");
		}
		clearText.append(name);
		clearText.append(" = ");
	}
}
