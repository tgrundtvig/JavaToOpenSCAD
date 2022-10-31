package org.abstractica.openscadcore.impl.cmdline;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Test
{
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
	{
		CmdLine.runCommand("openscad -o OpenSCAD/test.stl OpenSCAD/output.scad");
	}
}
