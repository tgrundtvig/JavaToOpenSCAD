package org.abstractica.openbuildsystem.generators.stdbricks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.*;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;

public class StdBricks
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private final ClickSystem clickSystem;
	private final double unitSize = 5;

	public StdBricks(CSG csg, Print3DAdjust adjust, ClickSystem clickSystem)
	{
		this.csg = csg;
		this.adjust = adjust;
		this.clickSystem = clickSystem;
	}

	public Module3D stdBrick(int xSize, int ySize)
	{
		Module3DFrom3D diff = csg.csg3D().construct3D().difference3D();
		diff.add(brick(xSize, ySize));
		for(int y = 0; y < ySize; ++y)
		{
			for(int x = 0; x < xSize; ++x)
			{
				double px = -xSize*unitSize+unitSize + x*2*unitSize;
				double py = -ySize*unitSize+unitSize + y*2*unitSize;
				Module3DFrom3D t = csg.csg3D().construct3D().translate3D(px, py, 0);
				t.add(clickSystem.crossClickerCutout(unitSize));
				diff.add(t);
			}
		}
		return diff;
	}

	private Module3D brick(int xSize, int ySize)
	{
		double x = xSize*2*unitSize + 2 * adjust.solidSquareTight().xy();
		double y = ySize*2*unitSize + 2 * adjust.solidSquareTight().xy();
		Module3D brick = csg.csg3D().shapes3D().box3D(x, y, unitSize);
		brick = csg.csg3D().construct3D().translate3D(0,0,0.5*unitSize).add(brick);
		return brick;
	}

}
