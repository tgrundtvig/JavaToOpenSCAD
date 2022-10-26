package org.abstractica.javatoopenscad.modulesimpl.csg3d.construct3d;

import org.abstractica.javatoopenscad.coreimpl.core.ArgumentCollector;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.plugininterfaces.Module3DFrom3DImpl;

import java.util.List;

public class RotateAround3D implements Module3DFrom3DImpl
{
	private final Angle ax;
	private final Angle ay;
	private final Angle az;
	private final double px;
	private final double py;
	private final double pz;

	public RotateAround3D(Angle ax, Angle ay, Angle az, double px, double py, double pz)
	{
		this.ax = ax;
		this.ay = ay;
		this.az = az;
		this.px = px;
		this.py = py;
		this.pz = pz;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("ax", ax);
		collector.add("ay", ay);
		collector.add("az", az);
		collector.add("px", px);
		collector.add("py", py);
		collector.add("pz", pz);
	}

	@Override
	public Module3DFrom3D buildGeometry(CSG csg, List<Module3D> children)
	{
		Module3DFrom3D t1 = csg.csg3D().construct3D().translate3D(-px, -py, -pz);
		for(Module3D child : children)
		{
			t1.add(child);
		}
		Module3DFrom3D rotate = csg.csg3D().construct3D().rotate3D(ax, ay, az).add(t1);
		Module3DFrom3D t2 = csg.csg3D().construct3D().translate3D(px, py, pz).add(rotate);
		return t2;
	}
}
