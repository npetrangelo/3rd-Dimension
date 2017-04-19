import java.awt.Color;
import java.awt.Point;

public class CameraOrthographic extends Camera
{
    private static int zoom = 3;
    
    @Override
    public Point project(CubePoint pt)
    {
        CubePoint image = transform(pt);
        return new Point((int) (image.x * zoom), (int) (image.y * zoom));
    }

	@Override
	public Color getCubeColor() {
		return Color.RED;
	}

	@Override
	public Color getIntersectionColor() {
		return Color.ORANGE;
	}

}
