package refactoring.modules.modulesimpl.builtin.polygon2d;

import refactoring.coreimpl.codebuilder.CodeBuilder;
import refactoring.coreimpl.core.ArgumentCollector;
import refactoring.coreimpl.core.BuiltInModule;
import refactoring.modules.modulesintf.csg2d.g2d.polygons.Path2D;
import refactoring.modules.modulesintf.math.Vector2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon2D implements BuiltInModule
{
	private static final int pointsPrLine = 5;
	private final List<Vector2D> vertices;
	private final List<Path2D> paths;
	public Polygon2D(Iterable<Vector2D> vertices, Iterable<Path2D> paths)
	{
		ArrayList<Vector2D> newVertices = new ArrayList<>();
		ArrayList<Path2D> newPaths = new ArrayList<>();
		for(Vector2D v : vertices)
		{
			newVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(newVertices);
		for (Path2D path : paths)
		{
			newPaths.add(path);
		}
		this.paths = Collections.unmodifiableList(newPaths);
	}

	public Polygon2D(Iterable<Vector2D> vertices)
	{
		ArrayList<Vector2D> newPoints = new ArrayList<>();
		for(Vector2D v : vertices)
		{
			newPoints.add(v);
		}
		this.vertices = Collections.unmodifiableList(newPoints);
		this.paths = null;
	}

	public List<Vector2D> getVertices()
	{
		return vertices;
	}

	public List<Path2D> getPaths()
	{
		return paths;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		//Vertices
		cb.println("polygon");
		cb.println("(");
		cb.indent();
		//-->>
		cb.println("points =");
		cb.println("[");
		cb.indent();
		for(int i = 0; i < vertices.size(); ++i)
		{
			Vector2D v = vertices.get(i);
			if(i > 0)
			{
				if(i % pointsPrLine == 0)
				{
					cb.println(",");
				}
				else
				{
					cb.print(", ");
				}
			}
			cb.print("[");
			cb.print(Double.toString(v.x()));
			cb.print(", ");
			cb.print(Double.toString(v.y()));
			cb.print("]");
		}
		cb.println();
		cb.undent();
		cb.println("]");


		//Paths
		if(paths != null && paths.size() > 0)
		{
			cb.println(",");
			cb.println("paths =");
			cb.println("[");
			cb.indent();
			//-->>
			boolean first2 = true;
			for(Path2D path : paths)
			{
				if(first2)
				{
					first2 = false;
				}
				else
				{
					cb.println(",");
				}
				cb.print("[");
				boolean first3 = true;
				for(Integer i : path)
				{
					if(first3)
					{
						first3 = false;
					}
					else
					{
						cb.print(",");
					}
					cb.print(Integer.toString(i));
				}
				cb.print("]");
			}
			cb.println();
			cb.undent();
			//<<--
			cb.println("]");
		}

		cb.undent();
		//<<--
		cb.print(")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("polygon2D", this);
	}
}
