import java.awt.Point;
import java.awt.Graphics2D;

public class CubePoint
{
    public double x, y, z;
    
    public CubePoint(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static CubePoint makePoint(double[] coords)
    {
        return new CubePoint(coords[0], coords[1], coords[2]);
    }
    
    public Point project()
    {
        return new Point((int) (App.focalLength * x/z), (int) (App.focalLength * y/z));
    }
    
    public void translate(double dx, double dy, double dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }
    
    public void Draw(Graphics2D g2d)
    {
        Point ppt =  project();
        g2d.fillOval(ppt.x - 4, ppt.y - 4, 8, 8);
    }
    
    public double[] getMatrix()
    {
        return new double[] {this.x, this.y, this.z};
    }
    
    public String toString()
    {
        return this.getClass().getName() + "[" + x + "," + y + "," + z + "]";
    }
}
