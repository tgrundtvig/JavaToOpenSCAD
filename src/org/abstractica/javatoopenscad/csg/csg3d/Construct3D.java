package org.abstractica.javatoopenscad.csg.csg3d;

import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom2D;
import org.abstractica.javatoopenscad.coreimpl.core.moduletypes.Module3DFrom3D;
import org.abstractica.javatoopenscad.csg.Angle;

public interface Construct3D
{
	Module3DFrom3D translate3D(double x, double y, double z);
	Module3DFrom3D translate3D(Vector3D t);
	Module3DFrom3D translate3D(Polar3D p);
	Module3DFrom3D rotate3D(Angle x, Angle y, Angle z);
	Module3DFrom3D rotateX(Angle a);
	Module3DFrom3D rotateY(Angle a);
	Module3DFrom3D rotateZ(Angle a);
	Module3DFrom3D rotateAround3D(Angle ax, Angle ay, Angle az, double px, double py, double pz);
	Module3DFrom3D rotateAroundX(Angle a, double py, double pz);
	Module3DFrom3D rotateAroundY(Angle a, double px, double pz);
	Module3DFrom3D rotateAroundZ(Angle a, double px, double py);
	Module3DFrom3D scale3D(double x, double y, double z);
	Module3DFrom3D scale3D(Vector3D s);
	Module3DFrom3D resize3D(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ);
	Module3DFrom3D resize3D(Vector3D s, boolean autoX, boolean autoY, boolean autoZ);
	Module3DFrom3D mirror3D(double normX, double normY, double normZ);
	Module3DFrom3D mirror3D(Vector3D norm);
	Module3DFrom3D mirror3D(Polar3D norm);
	Module3DFrom3D union3D();
	Module3DFrom3D intersection3D();
	Module3DFrom3D difference3D();
	Module3DFrom3D hull3D();
	Module3DFrom3D minkowsky3D();
	// Linear extrude
	Module3DFrom2D linearExtrude(double height, Angle twist, double scale, int slices, int convexity);
	Module3DFrom2D linearExtrude(double height, int convexity);
	Module3DFrom2D linearExtrude(double height, double scale, int convexity);

	// Rotate extrude
	Module3DFrom2D rotateExtrude(Angle angle, int angularResolution, int convexity);
}
