import java.awt.Graphics2D;
import java.util.ArrayList;

public class Intersection2D
{
    private ArrayList<Line> edges = new ArrayList<Line>();
    
    public Intersection2D(ArrayList<Line> lines)
    {
        edges = lines;
    }
    
    public void Draw(Graphics2D g2d, Camera p)
    {
        for (Line edge : edges)
        {
            edge.Draw(g2d, p);
        }
    }
}
