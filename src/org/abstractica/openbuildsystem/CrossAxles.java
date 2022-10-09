package org.abstractica.openbuildsystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.*;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.csg2d.Vector2D;
import org.abstractica.javatoopenscad.csg.csg3d.Vector3D;

public class CrossAxles
{
	public Module2D axleProfile(double diameter, double offset, CSG csg)
	{
		Module2D rect1 = csg.csg2D().shapes2D().rectCorners2D(Vector2D.create(-1.0/3.0, -1.5),
															Vector2D.create(1.0/3.0, 1.5));
		Module2D rect2 = csg.csg2D().shapes2D().rectCorners2D(Vector2D.create(-1.5, -1.0/3.0),
															Vector2D.create(1.5, 1.0/3.0));
		Module2DFrom2D union = csg.csg2D().construct2D().union2D().add(rect1).add(rect2);
		Module2DFrom2D intersection = csg.csg2D().construct2D().intersection2D().add(union);
		intersection.add(csg.csg2D().shapes2D().circle2D(2, 1024));
		Module2DFrom2D scale = csg.csg2D().construct2D().scale2D(0.5*diameter, 0.5*diameter).add(intersection);
		Module2DFrom2D off = csg.csg2D().construct2D().offsetRound2D(offset, 16);
		off.add(scale);
		return off;
	}

	public Module3D axleTip(double diameter, CSG csg, Adjust adjust)
	{
		double height = diameter*0.2;
		Module2D profile = axleProfile(diameter/1.1, adjust.getXYAdjust(), csg);
		Module3DFrom2D extrude = csg.csg3D().construct3D().linearExtrude(height, 1.1, 2).add(profile);
		Module3D res = csg.csg3D().construct3D().translate3D(0,0,-0.5*height).add(extrude);
		return res;
	}

	public Module3D axleCutout(double diameter, double length, CSG csg, Adjust adjust)
	{
		Module2D profile = axleProfile(diameter, adjust.getXYHoleAdjust(), csg);
		return csg.csg3D().construct3D().linearExtrude(length, 4).add(profile);
	}

	public Module3D axle(double diameter, double length, boolean bottomTip, boolean topTip, CSG csg, Adjust adjust)
	{
		Module2D profile = axleProfile(diameter, adjust.getXYAdjust(), csg);
		Module3D axle = csg.csg3D().construct3D().linearExtrude(length, 4).add(profile);
		Module3D tipBottom = csg.csg3D().construct3D().translate3D(0,0,-0.5*length)
				.add(axleTip(diameter, csg, adjust));
		Module3D tipTop = csg.csg3D().construct3D().mirror3D(Vector3D.create(0,0,1)).add(tipBottom);
		Module3DFrom3D union = csg.csg3D().construct3D().union3D().add(axle);
		if(bottomTip) union.add(tipBottom);
		if(topTip) union.add(tipTop);
		return union;
	}

	public Module3D getAxleCutoutTest(double diameter, double length, CSG csg, Adjust adjust)
	{
		Module3D cyl = csg.csg3D().shapes3D().cylinder3D(diameter + 8, length, 1024);
		return csg.csg3D().construct3D().difference3D().add(cyl)
				.add(axleCutout(diameter, length+2, csg, adjust));
	}



}
