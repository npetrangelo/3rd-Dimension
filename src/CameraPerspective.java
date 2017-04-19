import java.awt.Point;

public class CameraPerspective extends Camera
{
    private static int focalLength = 500;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = transform(pt);
        return new Point((int) (image.x * focalLength/image.z), (int) (image.y * focalLength/image.z));
    }
}
