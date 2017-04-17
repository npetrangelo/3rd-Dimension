
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
    private JPanel cubePanel;
    private JPanel camPanel;
    
    private JButton vertex;
    private JButton edge;
    private JButton face;
    private JButton top;
    private JButton front;
    
    private static Window instance;
    private App app;
    Camera cam;
    Framework framework;
                
    private Window()
    {
        cam = new CameraPerspective();
//        cam = new CameraOrthographic();
        
        // Sets the title for this frame.
        this.setTitle("3D Transformation");
        
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
        cubePanel = new JPanel();
        camPanel = new JPanel();
        
        slider = new JSlider(-50, 50, 0);
        
        face = new JButton("Face");
        face.addActionListener(this);
        cubePanel.add(face);
        
        edge = new JButton("Edge");
        edge.addActionListener(this);
        cubePanel.add(edge);
        
        vertex = new JButton("Vertex");
        vertex.addActionListener(this);
        cubePanel.add(vertex);
        
        cubePanel.add(slider);
        framework = new Framework(cam);
        
        UIPanel.add(cubePanel);
        
        top = new JButton("Top");
        top.addActionListener(this);
        camPanel.add(top);
        
        front = new JButton("Front");
        front.addActionListener(this);
        camPanel.add(front);
        
        UIPanel.add(camPanel);
        
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
        if (e.getSource() == top)
        {
            cam.setRotation(0);
        }
        if (e.getSource() == front)
        {
            cam.setRotation(-80);
        }
    }
}

