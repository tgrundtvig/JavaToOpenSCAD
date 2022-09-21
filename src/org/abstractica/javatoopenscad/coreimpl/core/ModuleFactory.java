package org.abstractica.javatoopenscad.coreimpl.core;
import org.abstractica.javatoopenscad.plugininterfaces.*;

public interface ModuleFactory
{
	//User build modules
	Module2D module2D(Module2DImpl impl);
	Module2DFrom2D module2DFrom2D(Module2DFrom2DImpl impl);
	Module2DFrom3D module2DFrom3D(Module2DFrom3DImpl impl);
	Module3D module3D(Module3DImpl impl);
	Module3DFrom2D module3DFrom2D(Module3DFrom2DImpl impl);
	Module3DFrom3D module3DFrom3D(Module3DFrom3DImpl impl);

	//Built in modules
	Module2D module2D(BuiltInModule impl);
	Module2DFrom2D module2DFrom2D(BuiltInModule impl);
	Module2DFrom3D module2DFrom3D(BuiltInModule impl);
	Module3D module3D(BuiltInModule impl);
	Module3DFrom2D module3DFrom2D(BuiltInModule impl);
	Module3DFrom3D module3DFrom3D(BuiltInModule impl);
}
