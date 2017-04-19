import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
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
    
    private Point prevMousePosition;
    
    private Cube cube = new Cube(50);
    private Intersection2D intersection;
    
    Window window;
    private static App instance;
    
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
        window = Window.getInstance();
        System.out.println("Just got instance: " + window);
        instance = this;
    }
    
    public static App getInstance()
    {
        return instance;
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
        cube.reset();
    }
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     */
    public void UpdateGame(long gameTime, Point deltaMousePosition)
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
        cube.rotateZ(deltaMousePosition.x);
        cube.rotateX(deltaMousePosition.y);
        cube.setZ(window.getSliderValue());
//        cube.translate(0, 0, 1);
        cube.updateFaces();
//        cube.updateIntersections(150);
        intersection = cube.getIntersection(0.0);
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d, Camera camera)
    {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Framework.frameWidth, Framework.frameHeight);
        g2d.translate(centerX, centerY);
        g2d.setColor(camera.getCubeColor());
        cube.Draw(g2d, camera);
        g2d.setColor(camera.getIntersectionColor());
        if (intersection != null)
        {
            intersection.Draw(g2d, camera);
        }
    }
    
    public void faceFirst()
    {
        cube.reset();
    }
    
    public void edgeFirst()
    {
        faceFirst();
        cube.rotateY(45);
    }
    
    public void vertexFirst()
    {
        edgeFirst();
        cube.rotateX(35);
    }
}
