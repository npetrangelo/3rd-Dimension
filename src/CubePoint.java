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
    
    public void translate(double dx, double dy, double dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }
    
    public void Draw(Graphics2D g2d, Camera p)
    {
        Point ppt =  p.project(this);
        g2d.fillOval(ppt.x - 4, ppt.y - 4, 8, 8);
    }
    
    public double[] getMatrix()
    {
        return new double[] {this.x, this.y, this.z};
    }
    
    public void rotateX(double a, CubePoint axis)
    {
        a *= Math.PI/180;
        double[][] matrix = {
                {1.0, 0.0,          0.0},
                {0.0, Math.cos(a), -Math.sin(a)},
                {0.0, Math.sin(a),  Math.cos(a)}
        };
        x -= axis.x;
        y -= axis.y;
        z -= axis.z;
        double[] rotation = matrixMultiply(matrix);
        x = rotation[0] + axis.x;
        y = rotation[1] + axis.y;
        z = rotation[2] + axis.z;
    }
    
    public void rotateY(double a, CubePoint axis)
    {
        a *= Math.PI/180;
        double[][] matrix = {
                {Math.cos(a),  0.0, Math.sin(a)},
                {0.0,          1.0, 0.0},
                {-Math.sin(a), 0.0, Math.cos(a)}
        };
        x -= axis.x;
        y -= axis.y;
        z -= axis.z;
        double[] rotation = matrixMultiply(matrix);
        x = rotation[0] + axis.x;
        y = rotation[1] + axis.y;
        z = rotation[2] + axis.z;
    }
    
    public void rotateZ(double a, CubePoint axis)
    {
        a *= Math.PI/180;
        double[][] matrix = {
        		{Math.cos(a), -Math.sin(a),0.0},
                {Math.sin(a), Math.cos(a), 0.0},
                {0.0,         0.0,         1.0}
        };
        x -= axis.x;
        y -= axis.y;
        z -= axis.z;
        double[] rotation = matrixMultiply(matrix);
        x = rotation[0] + axis.x;
        y = rotation[1] + axis.y;
        z = rotation[2] + axis.z;
    }
    
    public double[] matrixMultiply(double[][] matrix)
    {
        double[] pt = {0.0, 0.0, 0.0};
        
        if (matrix[0].length != 3)
        {
            throw new IllegalArgumentException("Matrix was not 3 columns wide.");
        }
        
        for (int i = 0; i < 3; i++) { // matrix row
            for (int j = 0; j < 3; j++) { // matrix column and coordinate
                pt[i] += getMatrix()[j] * matrix[i][j];
            }
        }
        return pt;
    }
    
    public String toString()
    {
        return this.getClass().getName() + "[" + x + "," + y + "," + z + "]";
    }

    public int compareByX(CubePoint that)
    {
        if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int compareByY(CubePoint that)
    {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int compareByZ(CubePoint that)
    {
        if (this.z < that.z) {
            return -1;
        } else if (this.z > that.z) {
            return 1;
        } else {
            return 0;
        }
    }
}