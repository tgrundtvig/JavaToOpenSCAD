package org.abstractica.openbuildsystem;

public class AdjustImpl implements Adjust
{
	private final double xyHoleAdjust;
	private final double zHoleAdjust;
	private final double xyAdjust;
	private final double zAdjust;

	public AdjustImpl(double xyHoleAdjust, double zHoleAdjust, double xyAdjust, double zAdjust)
	{
		this.xyHoleAdjust = xyHoleAdjust;
		this.zHoleAdjust = zHoleAdjust;
		this.xyAdjust = xyAdjust;
		this.zAdjust = zAdjust;
	}

	@Override
	public double getXYHoleAdjust()
	{
		return xyHoleAdjust;
	}

	@Override
	public double getZHoleAdjust()
	{
		return zHoleAdjust;
	}

	@Override
	public double getXYAdjust()
	{
		return xyAdjust;
	}

	@Override
	public double getZAdjust()
	{
		return zAdjust;
	}
}
