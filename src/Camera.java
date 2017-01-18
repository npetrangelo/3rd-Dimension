import java.awt.Point;

public abstract class Camera
{
    private double x = 0.0;
    private double y = 0.0;
    private double z = -150.0;
    private double theta = 0.0;
    private double phi = 0.0;
    
    public abstract Point project(CubePoint point);
    
    public CubePoint transform(CubePoint point)
    {
        CubePoint image = new CubePoint(point.x, point.y, point.z);
        CubePoint axis = new CubePoint(0.0, 0.0, 0.0);
        image.rotateY(theta, axis);
        image.rotateX(phi, axis);
        image.translate(-x, -y, -z);
        return image;
    }
    
    public void rotate(double dTheta, double dPhi)
    {
        theta += dTheta;
        phi += dPhi;
    }
    
    public void translate(double dx, double dy, double dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }
}
