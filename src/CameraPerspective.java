import java.awt.Point;

public class CameraPerspective implements Camera
{
    private static int focalLength = 500;
    private double x = 0.0;
    private double y = 0.0;
    private double z = -150.0;
    private double theta = 0.0;
    private double phi = 0.0;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = new CubePoint(pt.x, pt.y, pt.z);
        CubePoint axis = new CubePoint(0.0, 0.0, 0.0);
        image.rotateY(theta, axis);
        image.rotateX(phi, axis);
        image.translate(-x, -y, -z);
        return new Point((int) (focalLength * image.x/image.z), (int) (focalLength * image.y/image.z));
    }
    
    public void rotate(double dTheta, double dPhi)
    {
        theta += dTheta;
        phi += dPhi;
    }
}
