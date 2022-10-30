package org.abstractica.openbuildsystem.generators.sourced.ballbearings;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;
import org.abstractica.javatoopenscad.csg.CSG;
import org.abstractica.javatoopenscad.csg.math.Angle;
import org.abstractica.openbuildsystem.Print3DAdjust;

public class DeepGrooveBallBearing
{
	//Parameters
	private final double innerDiameter;
	private final double outerDiameter;
	private final double height;
	private final double innerShoulder;
	private final double outerShoulder;
	private final double shoulderHeight;


	//CSG and print adjust
	private final CSG csg;
	private final Print3DAdjust adjust;


	public DeepGrooveBallBearing(
			CSG csg,
			Print3DAdjust adjust,
			double innerDiameter,
			double outerDiameter,
			double height,
			double innerShoulder,
			double outerShoulder,
			double shoulderHeight
			)
	{
		this.csg = csg;
		this.adjust = adjust;
		this.innerDiameter = innerDiameter;
		this.outerDiameter = outerDiameter;
		this.height = height;
		this.innerShoulder = innerShoulder;
		this.outerShoulder = outerShoulder;
		this.shoulderHeight = shoulderHeight;
	}

	public Module3D bb()
	{
		return null;
	}

	public Module3D bbCutout()
	{
		return null;
	}

	private Module3D bbCutoutSimple()
	{
		return csg.csg3D().construct3D().rotateY(Angle.degrees(90)).add
				(
						csg.csg3D().shapes3D().cylinder3D
								(
										22 + 2*adjust.holeRoundTight().z(),
										7 + 2*adjust.holeSquareTight().xy(),
										128
								)
				);
	}
}
