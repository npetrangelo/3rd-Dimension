import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Framework that controls the game (Game.java) that created it, update it and draw it on the screen.
 * 
 * @author www.gametutorial.net
 */

public class Framework extends JPanel implements MouseMotionListener {
    private static final long serialVersionUID = 7230961570221429249L;

    /**
     * Width of the frame.
     */
    public static int frameWidth = 1024;

    /**
     * Height of the frame.
     */
    public static int frameHeight = 748;

    /**
     * Time of one second in nanoseconds.
     * 1 second = 1 000 000 000 nanoseconds
     */
    public static final long secInNanosec = 1000000000L;
    
    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;
    
    /**
     * FPS - Frames per second
     * How many times per second the game should update?
     */
    private final int GAME_FPS = 60;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    private Point prevMousePosition;
    private Point deltaMousePosition;
    
    private Camera projector;
    
    private boolean isInitialized = false;
    
    /**
     * Elapsed game time in nanoseconds.
     */
    private long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;
    
    // The actual game
    private App app;
    
    
    public Framework(Camera p)
    {
        super();
        projector = p;
        addMouseMotionListener(this);
        deltaMousePosition = new Point(0, 0);
    }

    void startGameLoop()
    {
        //We start game in new thread.
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        gameThread.start();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(frameWidth, frameHeight);
    }
    
    
   /**
     * Set variables and objects.
     * This method is intended to set the variables and objects for this class, variables and objects for the actual game can be set in Game.java.
     */
    private void Initialize()
    {

    }
    
    /**
     * Load files - images, sounds, ...
     * This method is intended to load files for this class, files for the actual game can be loaded in Game.java.
     */
    private void LoadContent()
    {
    
    }
    
    
    /**
     * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is updated and then the game is drawn on the screen.
     */
    private void GameLoop()
    {
        // This two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
//        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;

        newGame();
//        System.out.println("GameLoop After newGame app.window=" + app.window);
        isInitialized = true;
        while(true)
        {
//            System.out.println("----- Main loop -----");
            gameTime += System.nanoTime() - lastTime;
            
//            System.out.println("GameLoop Before UpdateGame app.window=" + app.window);
            app.UpdateGame(gameTime, deltaMousePosition);
            
            lastTime = System.nanoTime();
            
            deltaMousePosition = new Point(0, 0);
            
            beginTime = System.nanoTime();
            
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10) 
                timeLeft = 10; //set a minimum
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    
    /**
     * Draw the game to the screen. It is called through repaint() method in GameLoop() method.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        if (isInitialized)
        {
            app.Draw((Graphics2D) g, projector);
        }
    }
    
    
    /**
     * Starts new game.
     */
    private void newGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        app = new App();
    }
    
    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    private void restartGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        app.RestartGame();
        
        // We change game status so that the game can start.
    }
    
    
//    /**
//     * Returns the position of the mouse pointer in game frame/window.
//     * If mouse position is null than this method return 0,0 coordinate.
//     * 
//     * @return Point of mouse coordinates.
//     */
//    private Point mousePosition()
//    {
//        try
//        {
//            Point mp = this.getMousePosition();
//            
//            if(mp != null)
//                return this.getMousePosition();
//            else
//                return new Point(0, 0);
//        }
//        catch (Exception e)
//        {
//            return new Point(0, 0);
//        }
//    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        System.out.println(e.getPoint());
        if (e.isShiftDown())
        {
            deltaMousePosition = new Point(-(e.getX() - prevMousePosition.x)/2, (e.getY() - prevMousePosition.y)/2);
        }
        else
        {
            deltaMousePosition = new Point(-(e.getX() - prevMousePosition.x), (e.getY() - prevMousePosition.y));
        }
        prevMousePosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        prevMousePosition = e.getPoint();
    }
}
