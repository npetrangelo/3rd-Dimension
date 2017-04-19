import java.awt.Color;
import java.awt.Point;

public class CameraPerspective extends Camera
{
    private static final int focalLength = 450;
    private double persp = 1.0;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = transform(pt);
        if (persp != 0.0)
        {
        	return new Point((int) (image.x * (focalLength * persp/image.z + 3*(1 - persp))),
        					 (int) (image.y * (focalLength * persp/image.z + 3*(1 - persp))));
        }
        else
        {
            return new Point((int) (image.x * 3), (int) (image.y * 3));
        }
    }

	@Override
	public Color getCubeColor() {
		return Color.BLUE;
	}

	@Override
	public Color getIntersectionColor() {
		return Color.CYAN;
	}
	
	public void setPerspValue(double n)
	{
		persp = n;
	}
}
