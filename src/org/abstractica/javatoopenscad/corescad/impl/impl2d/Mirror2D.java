package org.abstractica.javatoopenscad.corescad.impl.impl2d;

public class Mirror2D extends ANode2D
{
	private final double normX;
	private final double normY;

	public Mirror2D(double normX, double normY)
	{
		this.normX = normX;
		this.normY = normY;
	}

	public double getNormX()
	{
		return normX;
	}

	public double getNormY()
	{
		return normY;
	}
}
