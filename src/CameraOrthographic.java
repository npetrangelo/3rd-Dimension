import java.awt.Point;

public class CameraOrthographic implements Camera
{
    public static int zoom = 3;
    public double x = 0.0;
    public double y = 0.0;
    public double z = -150.0;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint point = new CubePoint(pt.x - x, pt.y - y, pt.z - z);
        return new Point((int) (zoom * point.x), (int) (zoom * point.y));
    }

}
