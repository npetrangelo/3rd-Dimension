import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class App {
//    private SunGraphics2D graphics;
    private double centerX = Window.WIDTH / 2;
    private double centerY = Window.HEIGHT / 2;
    private static int width = 400;
    private static int height = 400;
    public static int focalLength = 500;
    private double y1 = 1.0;
    private double y2 = 1.0;
    private double z1 = 1.0;
    private double z2 = 1.0;
    
    private Cube cube = new Cube(50);
    private ArrayList<Intersection2D> intersections = new ArrayList<Intersection2D>();

    
    private int increment = 0;
    
    public App()
    {        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();                
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        cube.rotateY(45);
        cube.rotateX(35);
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
    
    }    
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        cube.center();
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {

//        System.out.println(increment);
//        increment = ((increment + 1) % 360);
//        z1 = Math.cos(Math.toRadians(increment)) + 3;
//        z2 = Math.cos(Math.toRadians(increment + 180)) + 3;
//        y1 = Math.sin(Math.toRadians(increment)) * 50;
//        y2 = -y1;
//        cube1.translate(0, 0, 1);
//        cube.rotateY(1);
//        cube.rotateX(1);
        cube.translate(0, 0, 0.25);
        cube.updateFaces();
//        cube.updateIntersections(150);
        intersections.clear();
        for (int i = 170; i >= 150; i--)
        {
            intersections.add(cube.getIntersection(i));
        }
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.translate(centerX, centerY);
        g2d.setColor(Color.WHITE);
        cube.Draw(g2d);
        g2d.setColor(Color.BLUE);
        for (int i = 0; i <= 20; i++)
        {
            g2d.setColor(new Color(Color.HSBtoRGB(((float)i)/20, 1, 1)));
            Intersection2D intersection = intersections.get(i);
            if (intersection != null)
            {
                intersection.Draw(g2d);
            }
        }
        
//        g2d.drawLine(0, 100, increment, 100);
////        g2d.drawOval(-100, -100, 200, 200);
//        draw3dLine(g2d, width/2, 0, 3, -width/2, 0, 3);
//        draw3dLine(g2d, 0, y1, z1, 0, y2, z2);
//        draw3dLine(g2d, width/4, y1, z1, width/4, y2, z2);
//        draw3dLine(g2d, -width/4, y1, z1, -width/4, y2, z2);
//        draw3dLine(g2d, width/8, y1, z1, width/8, y2, z2);
//        draw3dLine(g2d, -width/8, y1, z1, -width/8, y2, z2);
//        draw3dLine(g2d, 3*width/8, y1, z1, 3*width/8, y2, z2);
//        draw3dLine(g2d, 3*-width/8, y1, z1, 3*-width/8, y2, z2);
//        
//        draw3dLine(g2d, width/2, y2, z2, width/2, y1, z1);
//        draw3dLine(g2d, -width/2, y1, z1, -width/2, y2, z2);
//        draw3dLine(g2d, -width/2, y1, z1, width/2, y1, z1);
//        draw3dLine(g2d, width/2, y2, z2, -width/2, y2, z2);
//        draw3dGrid(g2d, width, height, 5, 5, increment);
    }
    
//    public void draw3dLine(Graphics2D g2d, double x1, double y1, double z1, double x2, double y2, double z2)
//    {
//        g2d.drawLine((int)(x1/z1), (int)(y1/z1), (int)(x2/z2), (int)(y2/z2));
//    }
    
//    public void drawOrthagLine(Graphics2D g2d, double x1, double y1, double z1, double x2, double y2, double z2)
//    {
//        g2d.drawLine((int)(x1), (int)(y1), (int)(x2), (int)(y2));
//    }
    
//    /**
//     * 
//     * @param g2d The graphics object to draw with
//     * @param width Width of graph in pixels
//     * @param height Height of graph in pixels
//     * @param rows Number of rows in graph
//     * @param columns Number of Columns in graph
//     * @param angleX Angle rotated around X axis; 0 = horizontal
//     */
//    public void draw3dGrid(Graphics2D g2d, int width, int height, int rows, int columns, int angleX)
//    {
//        double z1 = Math.cos(Math.toRadians(angleX)) + 3;
//        double z2 = Math.cos(Math.toRadians(angleX + 180)) + 3;
//        
//        double y = Math.sin(Math.toRadians(angleX)) * height;
//        System.out.println("z=" + z1);
//        
//        for (int row = -height/2; row <= height/2; row += height/rows)
//        {
//            draw3dLine(g2d, width/2, y*row, z1*row, -width/2, y*row, z1*row);
//        }
//        drawOrthagLine(g2d, width/2, y*height/2, z1*height/2, -width/2, y*height/2, z1*height/2);
//        for (int column = -width/2; column <= width/2; column += width/columns)
//        {
//            draw3dLine(g2d, column, y, z1, column, -y, z2);
//        }
//    }
}
