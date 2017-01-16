import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Cube
{
    public int length;
    public CubePoint[][][] pts = new CubePoint[2][2][2];
    public ArrayList<Line> edges = new ArrayList<Line>();
    public ArrayList<Face> faces = new ArrayList<Face>();
    public ArrayList<Line> intersections = new ArrayList<Line>();
    public CubePoint center;
    
    public Cube(int length)
    {
        this.length = length;
        center = new CubePoint(0, 0, 0);
        reset();
        
        updateLines();
    }
    
    /**
     * Make sure you update before drawing!
     * @param g2d
     */
    public void Draw(Graphics2D g2d, Camera p)
    {
        for (Face face : faces)
        {
            face.Draw(g2d, p);
        }
    }
    
    public void reset()
    {
        center = new CubePoint(0, 0, 0);
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {
                    pts[i][j][k] = new CubePoint(i*length - length/2, j*length - length/2, k*length - length/2);
                }
            }
        }
    }
    
    public void updateCenter()
    {
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {
                    xSum += pts[i][j][k].x;
                    ySum += pts[i][j][k].y;
                    zSum += pts[i][j][k].z;
                }
            }
        }
        center = new CubePoint(xSum/8, ySum/8, zSum/8);
    }
    
    public void updateLines()
    {
        edges.clear();
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {                    
                    if (i + 1 < 2)
                    {
                        edges.add(new Line(pts[i][j][k], pts[i+1][j][k]));
                    }
                    if (j + 1 < 2)
                    {
                        edges.add(new Line(pts[i][j][k], pts[i][j+1][k]));
                    }
                    if (k + 1 < 2)
                    {
                        edges.add(new Line(pts[i][j][k], pts[i][j][k+1]));
                    }
                }
            }
        }
    }
    
    public void updateFaces()
    {
        updateLines();
        faces.clear();
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {                    
                    if (i+1 < 2 && j+1 < 2)
                    {
                        faces.add(Face.makeFace(new CubePoint[] {pts[i][j][k], pts[i+1][j][k], pts[i+1][j+1][k], pts[i][j+1][k]}));
                    }
                    
                    if (j+1 < 2 && k+1 < 2)
                    {
                        faces.add(Face.makeFace(new CubePoint[] {pts[i][j][k], pts[i][j+1][k], pts[i][j+1][k+1], pts[i][j][k+1]}));
                    }
                    
                    if (i+1 < 2 && k+1 < 2)
                    {
                        faces.add(Face.makeFace(new CubePoint[] {pts[i][j][k], pts[i+1][j][k], pts[i+1][j][k+1], pts[i][j][k+1]}));
                    }
                }
            }
        }
    }
    
    public Intersection2D getIntersection(double zPlane)
    {
        ArrayList<Line> intersection = new ArrayList<Line>();
        for (Face face : faces)
        {
            Line line = face.getIntersection(zPlane);
            if (line != null)
            {
                intersection.add(line);
            }
        }
        return new Intersection2D(intersection);
    }
    
    public void updateIntersections(double zPlane)
    {
//        for (Line edge : edges)
//        {
//            CubePoint intersectionPt = edge.makeIntersection(zPlane);
//            if (intersectionPt != null)
//            {
//                Point projected = intersectionPt.project();
//                intersection.addPoint(projected.x, projected.y);
//            }
//        }
        intersections.clear();
        for (Face face : faces)
        {
            Line intersection = face.getIntersection(zPlane);
            if (intersection != null)
            {
                intersections.add(intersection);
            }
        }
    }
    
    public void DrawIntersections(Graphics2D g2d, Camera p)
    {
        if (!intersections.isEmpty())
        {
            System.out.println(intersections);
            for (Line intersection : intersections)
            {
                intersection.Draw(g2d, p);
            }
        }
    }
    
    public void translate(double x, double y, double z)
    {
//        for (CubePoint[][] face : pts)
//        {
//            for (CubePoint[] edge : face)
//            {
//                for (CubePoint pt : edge)
//                {
//                    pt = new CubePoint(pt.x + x, pt.y + y, pt.z + z);
//                }
//            }
//        }
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {
                    pts[i][j][k] = new CubePoint(pts[i][j][k].x + x, pts[i][j][k].y + y, pts[i][j][k].z + z);
                }
            }
        }
        updateCenter();
    }
    
    public void setZ(double z)
    {
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {
                    pts[i][j][k] = new CubePoint(pts[i][j][k].x, pts[i][j][k].y, (pts[i][j][k].z - center.z) + z);
                }
            }
        }
        updateCenter();
    }

    public void rotateY(double a)
    {
        a *= Math.PI/180;
        double[][] matrix = {
                {Math.cos(a),  0.0, Math.sin(a)},
                {0.0,          1.0, 0.0},
                {-Math.sin(a), 0.0, Math.cos(a)}
        };
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {   
                    double[] pt = matrixMultiply(new CubePoint(pts[i][j][k].x - center.x, pts[i][j][k].y - center.y, pts[i][j][k].z - center.z), matrix);
                    pts[i][j][k] = CubePoint.makePoint(new double[] {pt[0] + center.x, pt[1] + center.y, pt[2] + center.z});
                }
            }
        }
    }
    
    public void rotateX(double a)
    {
        a *= Math.PI/180;
        double[][] matrix = {
                {1.0, 0.0,          0.0},
                {0.0, Math.cos(a), -Math.sin(a)},
                {0.0, Math.sin(a),  Math.cos(a)}
        };
        for (int i = 0; i <= 1; i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                for (int k = 0; k <= 1; k++)
                {   
                    double[] pt = matrixMultiply(new CubePoint(pts[i][j][k].x - center.x, pts[i][j][k].y - center.y, pts[i][j][k].z - center.z), matrix);
                    pts[i][j][k] = CubePoint.makePoint(new double[] {pt[0] + center.x, pt[1] + center.y, pt[2] + center.z});
                }
            }
        }
    }
    
    public double[] matrixMultiply(CubePoint p1, double[][] matrix)
    {
        double[] pt = {0.0, 0.0, 0.0};
        
        if (matrix[0].length != 3)
        {
            throw new IllegalArgumentException("Matrix was not 3 columns wide.");
        }
        
        for (int i = 0; i < 3; i++) { // matrix row
            for (int j = 0; j < 3; j++) { // matrix column and coordinate
                pt[i] += p1.getMatrix()[j] * matrix[i][j];
            }
        }
        return pt;
    }
}
