import java.awt.Point;

public class CameraPerspective implements Camera
{
    public static int focalLength = 500;
    public double x = 0.0;
    public double y = 0.0;
    public double z = -150.0;

    @Override
    public Point project(CubePoint pt)
    {
        CubePoint point = new CubePoint(pt.x - x, pt.y - y, pt.z - z);
        return new Point((int) (focalLength * point.x/point.z), (int) (focalLength * point.y/point.z));
    }
}
