
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * Creates frame and set its properties.
 * 
 * @author www.gametutorial.net
 */
public class Window extends JFrame
{
    public static int WIDTH = 1024;
    public static int HEIGHT = 850;
    
    public static JSlider slider;
    public JPanel panel;
                
    private Window()
    {
        // Sets the title for this frame.
        this.setTitle("4D Transformation");
        
        // Sets size of the frame.
        if(false) // Full screen mode
        {
            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        else // Window mode
        {
            // Size of the frame.
            this.setSize(WIDTH, HEIGHT);
//            this.pack();
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(true);
        }
        
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        slider = new JSlider(100, 500, 150);
        panel.add(new Framework());
        panel.add(slider);
        // Creates the instance of the Framework.java that extends the Canvas.java and puts it on the frame.
        this.add(panel);
        
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}

