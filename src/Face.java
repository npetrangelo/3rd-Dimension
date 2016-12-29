import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;

public class Face
{
    public ArrayList<CubePoint> points;
    public ArrayList<Line> edges;
    public ArrayList<CubePoint> intersections = new ArrayList<CubePoint>();
    public Polygon projection = new Polygon();
    
    public Face(ArrayList<CubePoint> points)
    {
        this.points = points;
        edges = new ArrayList<Line>();
        
        for (int i = 0; i < points.size() - 1; i++)
        {
            edges.add(new Line(points.get(i), points.get(i+1)));
        }
        edges.add(new Line(points.get(points.size() - 1), points.get(0)));
    }
    
    public static Face makeFace(CubePoint[] pts)
    {
        return new Face(new ArrayList<CubePoint>(Arrays.asList(pts)));
    }
    
    public void Draw(Graphics2D g2d)
    {
        for (CubePoint pt : points)
        {
            Point projected = pt.project();
            projection.addPoint(projected.x, projected.y);
        }
        g2d.drawPolygon(projection);
    }
    
    public void updateIntersections(double zPlane)
    {
        intersections.clear();
        for (Line edge : edges)
        {
            CubePoint intersection = edge.makeIntersection(zPlane);
            if (intersection != null)
            {
                System.out.println("Face.updateIntersections intersection=" + intersection);
                intersections.add(intersection);
            }
        }
        intersections.sort(CubePoint::compareByX);
        System.out.println("Face.updateIntersections intersections=" + intersections);
    }
    
    public Line getIntersection(double zPlane)
    {
        ArrayList<CubePoint> intersections = new ArrayList<CubePoint>();
        for (Line edge : edges)
        {
            CubePoint intersection = edge.makeIntersection(zPlane);
            if (intersection != null)
            {
                System.out.println("Face.getIntersection intersection=" + intersection);
                intersections.add(intersection);
            }
        }
        intersections.sort(CubePoint::compareByX);
        if (intersections.isEmpty())
        {
                return null;
        }
        return new Line(intersections.get(0), intersections.get(1));
    }
    
    public void drawIntersection(Graphics2D g2d)
    {
        System.out.println(intersections.size());
        if (!intersections.isEmpty())
        {
            for (int i = 0; i <= intersections.size()-2; i += 2)
            {
                Line intersection = new Line(intersections.get(i), intersections.get(i+1));
                intersection.Draw(g2d);
            }
        }
    }

    @Override
    public String toString()
    {
        return "Face [points=" + points + ", edges=" + edges + ", intersections=" + intersections + ", projection=" + projection
                + "]";
    }
}
