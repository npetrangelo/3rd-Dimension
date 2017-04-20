import java.awt.Color;
import java.awt.Point;

public class CameraPerspective extends Camera
{
    private static final int focalLength = 450;
    private static final int orthoZoom = 3;
    private double factor = 1.0;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = transform(pt);
        double projectionConstant = mix(focalLength/image.z, orthoZoom, factor);
    	return new Point((int) (image.x * projectionConstant),
    					 (int) (image.y * projectionConstant));
    }

	@Override
	public Color getCubeColor() {
		return Color.BLUE;
	}

	@Override
	public Color getIntersectionColor() {
		return Color.CYAN;
	}
	
	public void setProjectionFactor(double factor)
	{
		this.factor = factor;
	}
	
	/**
	 * @param value1 The first value to mix
	 * @param value2 The second value to mix
	 * @param factor 0 = completely value2, 1 is completely value1
	 * @return The mixed value
	 */
	private double mix(double value1, double value2, double factor)
	{
		return (value1 * factor) + (value2 * (1 - factor));
	}
}
