
import java.awt.Point;

@FunctionalInterface
public interface Camera
{
    Point project(CubePoint point);
    
    
}
