package refactoring.modulesimpl.csg2d.g2dfrom2d;


import refactoring.core.Module2DFrom2D;
import refactoring.core.ModuleFactory;
import refactoring.modules.csg2d.g2dfrom2d.G2DFrom2D;
import refactoring.modulesimpl.AModuleFactory;

public class G2DFrom2DImpl extends AModuleFactory implements G2DFrom2D
{
	public G2DFrom2DImpl(ModuleFactory factory)
	{
		super(factory);
	}

	@Override
	public Module2DFrom2D translate(double x, double y)
	{
		return module2DFrom2D(new Translate2D(x, y));
	}
}
