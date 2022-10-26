package org.abstractica.openbuildsystem;

public interface Print3DAdjust
{
	//Hole adjustments
	//Round holes
	Adjust holeRoundLoose();
	Adjust holeRoundTight();

	//Square holes
	Adjust holeSquareLoose();
	Adjust holeSquareTight();

	//Solid adjustments
	//Round solids
	Adjust solidRoundLoose();
	Adjust solidRoundTight();

	//Square solids
	Adjust solidSquareLoose();
	Adjust solidSquareTight();

	double layerHeight();
	double nozzleWidth();
}
