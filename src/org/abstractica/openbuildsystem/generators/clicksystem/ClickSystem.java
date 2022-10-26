package org.abstractica.openbuildsystem.generators.clicksystem;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3D;

public interface ClickSystem
{
	Module3D clicker(double length);
	Module3D clickerCutout(double length);
	Module3D roundClicker(double length);
	Module3D roundClickerCutout(double length);
	Module3D roundClickerCutoutVertical(double length);
}
