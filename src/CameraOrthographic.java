import java.awt.Point;

public class CameraOrthographic extends Camera
{
    private static int zoom = 3;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = transform(pt);
        return new Point((int) (zoom * image.x), (int) (zoom * image.y));
    }

}
