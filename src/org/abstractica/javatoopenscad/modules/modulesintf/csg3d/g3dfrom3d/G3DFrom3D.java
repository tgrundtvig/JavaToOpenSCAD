package org.abstractica.javatoopenscad.modules.modulesintf.csg3d.g3dfrom3d;

import org.abstractica.javatoopenscad.modules.modulesimpl.builtin.Color;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Angle;
import org.abstractica.javatoopenscad.coreimpl.core.Module3DFrom3D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Polar3D;
import org.abstractica.javatoopenscad.modules.modulesintf.math.Vector3D;

public interface G3DFrom3D
{
	Module3DFrom3D color3D(Color color);
	Module3DFrom3D translate3D(double x, double y, double z);
	Module3DFrom3D translate3D(Vector3D t);
	Module3DFrom3D translate3D(Polar3D p);
	Module3DFrom3D rotate3D(Angle x, Angle y, Angle z);
	Module3DFrom3D scale3D(double x, double y, double z);
	Module3DFrom3D scale3D(Vector3D s);
	Module3DFrom3D mirror3D(double normX, double normY, double normZ);
	Module3DFrom3D mirror3D(Vector3D norm);
	Module3DFrom3D mirror3D(Polar3D norm);
	Module3DFrom3D union3D();
	Module3DFrom3D intersection3D();
	Module3DFrom3D difference3D();
	Module3DFrom3D hull3D();
}
