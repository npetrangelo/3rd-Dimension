import java.awt.Point;

public class CameraOrthographic implements Camera
{
    public static int zoom = 3;
    
    @Override
    public Point project(CubePoint point)
    {
        return new Point((int) (zoom * point.x), (int) (zoom * point.y));
    }

}
