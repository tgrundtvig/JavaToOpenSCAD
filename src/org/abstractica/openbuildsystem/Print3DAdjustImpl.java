package org.abstractica.openbuildsystem;

public class Print3DAdjustImpl implements Print3DAdjust
{
	public static final Print3DAdjust defaultAdjust = new Print3DAdjustImpl
			(
					0.2,
					0.2,
					0.1,
					0.1,
					0.2,
					0.2,
					0.1,
					0.1,
					-0.2,
					-0.2,
					-0.1,
					-0.1,
					-0.2,
					-0.2,
					-0.1,
					-0.1,
					0.2,
					0.4
			);
	private final Adjust holeRoundLoose;
	private final Adjust holeRoundTight;
	private final Adjust holeSquareLoose;
	private final Adjust holeSquareTight;
	private final Adjust solidRoundLoose;
	private final Adjust solidRoundTight;
	private final Adjust solidSquareLoose;
	private final Adjust solidSquareTight;
	private final double layerHeight;
	private final double nozzleWidth;

	public Print3DAdjustImpl
			(
				double holeRoundLooseXY,
				double holeRoundLooseZ,
				double holeRoundTightXY,
				double holeRoundTightZ,
				double holeSquareLooseXY,
				double holeSquareLooseZ,
				double holeSquareTightXY,
				double holeSquareTightZ,
				double solidRoundLooseXY,
				double solidRoundLooseZ,
				double solidRoundTightXY,
				double solidRoundTightZ,
				double solidSquareLooseXY,
				double solidSquareLooseZ,
				double solidSquareTightXY,
				double solidSquareTightZ,
				double layerHeight,
				double nozzleWidth
			)
	{
		this.holeRoundLoose = new AdjustImpl(holeRoundLooseXY, holeRoundLooseZ);
		this.holeRoundTight = new AdjustImpl(holeRoundTightXY, holeRoundTightZ);
		this.holeSquareLoose = new AdjustImpl(holeSquareLooseXY, holeSquareLooseZ);
		this.holeSquareTight = new AdjustImpl(holeSquareTightXY, holeSquareTightZ);
		this.solidRoundLoose = new AdjustImpl(solidRoundLooseXY, solidRoundLooseZ);
		this.solidRoundTight = new AdjustImpl(solidRoundTightXY, solidRoundTightZ);
		this.solidSquareLoose = new AdjustImpl(solidSquareLooseXY, solidSquareLooseZ);
		this.solidSquareTight = new AdjustImpl(solidSquareTightXY, solidSquareTightZ);
		this.layerHeight = layerHeight;
		this.nozzleWidth = nozzleWidth;
	}

	@Override
	public Adjust holeRoundLoose()
	{
		return holeRoundLoose;
	}

	@Override
	public Adjust holeRoundTight()
	{
		return holeRoundTight;
	}

	@Override
	public Adjust holeSquareLoose()
	{
		return holeSquareLoose;
	}

	@Override
	public Adjust holeSquareTight()
	{
		return holeSquareTight;
	}

	@Override
	public Adjust solidRoundLoose()
	{
		return solidRoundLoose;
	}

	@Override
	public Adjust solidRoundTight()
	{
		return solidRoundTight;
	}

	@Override
	public Adjust solidSquareLoose()
	{
		return solidSquareLoose;
	}

	@Override
	public Adjust solidSquareTight()
	{
		return solidSquareTight;
	}

	@Override
	public double layerHeight()
	{
		return layerHeight;
	}

	@Override
	public double nozzleWidth()
	{
		return nozzleWidth;
	}

	private static class AdjustImpl implements Adjust
	{
		private final double xy;
		private final double z;

		private AdjustImpl(double xy, double z)
		{
			this.xy = xy;
			this.z = z;
		}

		@Override
		public double xy()
		{
			return xy;
		}

		@Override
		public double z()
		{
			return z;
		}
	}
}
