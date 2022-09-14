package org.abstractica.javatoopenscad.scad.impl;

import org.abstractica.javatoopenscad.scad.SCAD;
import org.abstractica.javatoopenscad.scad.Geometry;
import org.abstractica.javatoopenscad.scad.scad2d.Coord2D;
import org.abstractica.javatoopenscad.scad.SCADModule;
import org.abstractica.javatoopenscad.scad.scad2d.Vector2D;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public abstract class AModule extends AGeometry implements SCADModule
{
	private static Map<String, Integer> allStrings = new HashMap<>();
	private static int stringCount = 0;
	private static final SCAD scad = new SCADImpl();

	private String uniqueId;
	private String clearText;

	private Geometry geometry;

	public abstract void getParameters(ParameterCollector collector);
	public abstract Geometry generateGeometry(SCAD scad);

	public AModule()
	{
		uniqueId = null;
		geometry = null;
	}

	@Override
	public String getUniqueId()
	{
		if(uniqueId == null)
		{
			generateUniqueIDAndClearText();
		}
		return uniqueId;
	}

	@Override
	public String getClearText()
	{
		if(clearText == null)
		{
			generateUniqueIDAndClearText();
		}
		return clearText;
	}

	@Override
	public Geometry getGeometry()
	{
		if(geometry == null)
		{
			geometry = generateGeometry(scad);
		}
		return geometry;
	}

	private void generateUniqueIDAndClearText()
	{
		StringBuilder uniqueId = new StringBuilder();
		uniqueId.append("M");
		uniqueId.append(id(getClass().getName()));
		StringBuilder clearText = new StringBuilder();
		clearText.append(getClass().getSimpleName());
		clearText.append("(");
		ParamaterCollectorImpl collector = new ParamaterCollectorImpl(uniqueId, clearText);
		getParameters(collector);
		clearText.append(")");
		this.uniqueId = uniqueId.toString();
		this.clearText = clearText.toString();
	}

	private static int id(String str)
	{
		Integer id = allStrings.get(str);
		if(id == null)
		{
			allStrings.put(str, ++stringCount);
			id = stringCount;
		}
		return id;
	}


	private static class ParamaterCollectorImpl implements ParameterCollector
	{
		private StringBuilder uniqueId;
		private StringBuilder clearText;
		private boolean first = true;

		public ParamaterCollectorImpl(StringBuilder uniqueId, StringBuilder clearText)
		{
			this.uniqueId = uniqueId;
			this.clearText = clearText;
		}

		@Override
		public void add(String name, int i)
		{
			uniqueId.append("i").append(id(Integer.toString(i)));
			prefix(name);
			clearText.append(i);
		}

		@Override
		public void add(String name, double d)
		{
			uniqueId.append("d").append(id(d(d)));
			prefix(name);
			clearText.append(d(d));
		}

		@Override
		public void add(String name, String str)
		{
			uniqueId.append("s").append(id(str));
			prefix(name);
			clearText.append("\"");
			clearText.append(str);
			clearText.append("\"");
		}

		@Override
		public void add(String name, Vector2D vector)
		{
			uniqueId.append("cx");
			uniqueId.append(id(d(vector.x())));
			uniqueId.append("y").append(id(d(vector.y())));
			prefix(name);
			clearText.append(vector.toVector2DString());
		}

		@Override
		public void add(String name, SCADModule module)
		{
			uniqueId.append("m").append(id(module.getUniqueId()));
			prefix(name);
			clearText.append(module.getClearText());
		}


		public String getUniqueId()
		{
			return uniqueId.toString();
		}

		public String getClearText()
		{
			return clearText.toString();
		}

		private String d(double d)
		{
			return String.format(Locale.ENGLISH,"%.4f", d);
		}
		private void prefix(String name)
		{
			if(first)
			{
				first = false;
			}
			else
			{
				clearText.append(", ");
			}
			clearText.append(name);
			clearText.append(" = ");
		}
	}
}
