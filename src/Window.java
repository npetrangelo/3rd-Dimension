
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * Creates frame and set its properties.
 * 
 * @author www.gametutorial.net
 */
public class Window extends JFrame implements ActionListener
{
    public static int WIDTH = 1024;
    public static int HEIGHT = 850;
    
    private JSlider slider;
    
    private JPanel mainPanel;
    private JPanel UIPanel;
    
    private JButton vertex;
    private JButton edge;
    private JButton face;
    
    private static Window instance;
    private App app;
                
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
        
        mainPanel = new JPanel();
        UIPanel = new JPanel();
        
        slider = new JSlider(-50, 50, 0);
        
        face = new JButton("Face");
        face.addActionListener(this);
        UIPanel.add(face);
        
        edge = new JButton("Edge");
        edge.addActionListener(this);
        UIPanel.add(edge);
        
        vertex = new JButton("Vertex");
        vertex.addActionListener(this);
        UIPanel.add(vertex);
        
        UIPanel.add(slider);
        Framework framework = new Framework(new CameraPerspective());
//        Framework framework = new Framework(new CameraOrthographic());
//        Framework framework = new Framework((CubePoint point) -> {
//           return new Point((int) (500 * point.x/(point.y - 200)), (int)(500 * (point.z - 200)/(point.y - 200))); 
//        });
        mainPanel.add(framework);
        mainPanel.add(UIPanel);
        // Creates the instance of the Framework.java that extends the Canvas.java and puts it on the frame.
        this.add(mainPanel);
        
        this.setVisible(true);
        instance = this;
        framework.startGameLoop();
    }
    
    public static Window getInstance()
    {
        return instance;
    }
    
    public int getSliderValue()
    {
        return slider.getValue();
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        app = App.getInstance();
        if (e.getSource() == face)
        {
            app.faceFirst();
        }
        if (e.getSource() == edge)
        {
            app.edgeFirst();
        }
        if (e.getSource() == vertex)
        {
            app.vertexFirst();
        }
    }
}

