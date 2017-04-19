import java.awt.Graphics2D;
import java.awt.Point;

public class Line
{
    public CubePoint p1, p2;
    
    public Line(CubePoint p1, CubePoint p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public void Draw(Graphics2D g2d, Camera cam)
    {
        Point pt1 = cam.project(p1);
        Point pt2 = cam.project(p2);
        p1.Draw(g2d, cam);
        p2.Draw(g2d, cam);
        g2d.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
    }
    
    public CubePoint midpoint()
    {
        double x = (p1.x + p2.x)/2;
        double y = (p1.y + p2.y)/2;
        double z = (p1.z + p2.z)/2;

        return new CubePoint(x, y, z);
    }
    
    /**
     * @param p1 First point of line
     * @param p2 Second point of line
     * @param zPlane  Plane which line intersects
     */
    public CubePoint makeIntersection(double zPlane)
    {   
        double zPercent = (zPlane - p1.z)/(p2.z - p1.z); // 0 if p1 is at zPlane, 1 if p2 is at zPlane
        double x = (zPercent) * (p2.x - p1.x) + p1.x;
        double y = (zPercent) * (p2.y - p1.y) + p1.y;
        if ((zPlane > p1.z && zPlane < p2.z) || (zPlane < p1.z && zPlane > p2.z)) // if zPlane is between points
        {
            return new CubePoint(x, y, zPlane);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "Line [p1=" + p1 + ", p2=" + p2 + "]";
    }
}
