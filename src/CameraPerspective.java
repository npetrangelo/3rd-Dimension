import java.awt.Point;

public class CameraPerspective implements Camera
{
    public static int focalLength = 500;

    @Override
    public Point project(CubePoint point)
    {
        return new Point((int) (focalLength * point.x/point.z), (int) (focalLength * point.y/point.z));
    }
}
