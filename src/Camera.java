import java.awt.Point;

public abstract class Camera
{
    private double x = 0.0;
    private double y = 0.0;
    private double z = -150.0;
    private double phi = 0.0;
    
    public abstract Point project(CubePoint point);
    
    public CubePoint transform(CubePoint point)
    {
        CubePoint image = new CubePoint(point.x, point.y, point.z);
        CubePoint axis = new CubePoint(0.0, 0.0, 0.0);
        image.rotateX(phi, axis);
        image.translate(-x, -y, -z);
        return image;
    }
    
    public void rotate(double dPhi)
    {
        phi += dPhi;
    }
    
    public void setRotation(double phi)
    {
//    	double diff = this.phi - phi;
//    	while (this.phi != phi) {
//    		this.rotate(diff/10);
//    		try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	}
        this.phi = phi;
    }
    
    public void translate(double dx, double dy, double dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }
    
    public void setPosition(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
