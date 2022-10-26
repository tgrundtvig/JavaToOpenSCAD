package org.abstractica.openbuildsystem.generators.trainsystem.tracks;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg3d.Construct3D;
import org.abstractica.openbuildsystem.Print3DAdjust;
import org.abstractica.openbuildsystem.generators.clicksystem.ClickSystem;

public class TrackMount
{
	private final CSG csg;
	private final Print3DAdjust adjust;
	private final ClickSystem cs;
	private final double mountXSize = 10;
	private final double mountYSize = 10;
	private final double mountZSize = 5;


	public TrackMount(CSG csg, Print3DAdjust adjust, ClickSystem cs)
	{
		this.csg = csg;
		this.adjust = adjust;
		this.cs = cs;
	}

	public Module3D beginMount()
	{
		return csg.csg3D().construct3D().translate3D(0,0.5*mountYSize, 0).add(mount());
	}

	public Module3D endMount()
	{
		return csg.csg3D().construct3D().translate3D(0,-0.5*mountYSize, 0).add(mount());
	}

	public Module3D doubleMount()
	{
		return csg.csg3D().construct3D().union3D().add(beginMount()).add(endMount());
	}

	public Module3D leftBeginMount()
	{
		return csg.csg3D().construct3D().translate3D(0,0.5*mountYSize, 0).add(leftMount());
	}

	public Module3D leftEndMount()
	{
		return csg.csg3D().construct3D().translate3D(0,-0.5*mountYSize, 0).add(leftMount());
	}

	public Module3D leftDoubleMount()
	{
		return csg.csg3D().construct3D().union3D().add(leftBeginMount()).add(leftEndMount());
	}

	public Module3D rightBeginMount()
	{
		return csg.csg3D().construct3D().translate3D(0,0.5*mountYSize, 0).add(rightMount());
	}

	public Module3D rightEndMount()
	{
		return csg.csg3D().construct3D().translate3D(0,-0.5*mountYSize, 0).add(rightMount());
	}

	public Module3D rightDoubleMount()
	{
		return csg.csg3D().construct3D().union3D().add(rightBeginMount()).add(rightEndMount());
	}

	private Module3D leftMount()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3D base = csg.csg3D().shapes3D().box3D(mountXSize, mountYSize, mountZSize);
		base = c3d.translate3D(-0.5*mountXSize,0, 0.5*mountZSize).add(base);
		Module3DFrom3D diff = c3d.difference3D().add(base);
		Module3D clickerCutout = cs.clickerCutout(mountZSize);
		diff.add(c3d.translate3D(-0.5*mountXSize,0,0).add(clickerCutout));
		return diff;
	}

	private Module3D rightMount()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3D base = csg.csg3D().shapes3D().box3D(mountXSize, mountYSize, mountZSize);
		base = c3d.translate3D(0.5*mountXSize,0, 0.5*mountZSize).add(base);
		Module3DFrom3D diff = c3d.difference3D().add(base);
		Module3D clickerCutout = cs.clickerCutout(mountZSize);
		diff.add(c3d.translate3D(0.5*mountXSize,0,0).add(clickerCutout));
		return diff;
	}

	private Module3D mount()
	{
		Construct3D c3d = csg.csg3D().construct3D();
		Module3D base = csg.csg3D().shapes3D().box3D(2*mountXSize, mountYSize, mountZSize);
		base = c3d.translate3D(0,0, 0.5*mountZSize).add(base);
		Module3DFrom3D diff = c3d.difference3D().add(base);
		Module3D clickerCutout = cs.clickerCutout(mountZSize);
		diff.add(c3d.translate3D(-0.5*mountXSize,0,0).add(clickerCutout));
		diff.add(c3d.translate3D(0.5*mountXSize,0,0).add(clickerCutout));
		return diff;
	}
}
