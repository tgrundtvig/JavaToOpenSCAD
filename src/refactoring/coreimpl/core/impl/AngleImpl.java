package refactoring.coreimpl.core.impl;

import refactoring.coreimpl.core.HasArguments;
import refactoring.modules.modulesintf.math.Angle;
import refactoring.coreimpl.core.ArgumentCollector;

public class AngleImpl implements HasArguments, Angle
{
	private final double rotations;

	public AngleImpl(double rotations)
	{
		this.rotations = rotations;
	}

	public double asRadians()
	{
		return rotations*(Math.PI*2);
	}

	public double asDegrees()
	{
		return rotations*360.0;
	}

	public double asRotations()
	{
		return rotations;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add("rotations", rotations);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Angle)) return false;

		Angle angle = (Angle) o;

		return Double.compare(angle.asRotations(), asRotations()) == 0;
	}

	@Override
	public int hashCode()
	{
		long temp = Double.doubleToLongBits(asRotations());
		return (int) (temp ^ (temp >>> 32));
	}
}
