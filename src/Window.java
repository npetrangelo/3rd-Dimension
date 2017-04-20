
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * Creates frame and set its properties.
 */
public class Window extends JFrame implements ActionListener
{
	public static int WIDTH = 896;
    public static int HEIGHT = 693;
    
    private Hashtable<Integer, JLabel> zLabels;
    private JSlider zSlider;
    
    private Hashtable<Integer, JLabel> camLabels;
    private JSlider camSlider;
    
    private Border lineBorder;
    
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
        cam = new Camera();
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
        
        lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        mainPanel = new JPanel();
        UIPanel = new JPanel();
        cubePanel = new JPanel();
        camPanel = new JPanel();
        
        zLabels = new Hashtable<Integer, JLabel>();
        zLabels.put(new Integer(-50), new JLabel("Near"));
        zLabels.put(new Integer(0), new JLabel("Mid"));
        zLabels.put(new Integer(50), new JLabel("Far"));
        
        zSlider = new JSlider(-50, 50, 0);
        zSlider.setMajorTickSpacing(25);
        zSlider.setMinorTickSpacing(5);
        zSlider.setPaintTicks(true);
        zSlider.setPaintLabels(true);
        zSlider.setLabelTable(zLabels);
        
        camLabels = new Hashtable<Integer, JLabel>();
        camLabels.put(new Integer(0), new JLabel("Orthographic"));
        camLabels.put(new Integer(100), new JLabel("Perspective"));
        
        camSlider = new JSlider(0, 100, 100);
        camSlider.setMajorTickSpacing(25);
        camSlider.setMinorTickSpacing(5);
        camSlider.setPaintTicks(true);
        camSlider.setPaintLabels(true);
        camSlider.setLabelTable(camLabels);
        
        face = new JButton("Face");
        face.addActionListener(this);
        cubePanel.add(face);
        
        edge = new JButton("Edge");
        edge.addActionListener(this);
        cubePanel.add(edge);
        
        vertex = new JButton("Vertex");
        vertex.addActionListener(this);
        cubePanel.add(vertex);
        
        cubePanel.add(zSlider);
        framework = new Framework(cam);
        
        cubePanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Cube Controls"));
        UIPanel.add(cubePanel);
        
        top = new JButton("Top");
        top.addActionListener(this);
        camPanel.add(top);
        
        front = new JButton("Front");
        front.addActionListener(this);
        camPanel.add(front);
        
        camPanel.add(camSlider);
        camPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Camera Controls"));
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
    
    public int getZValue()
    {
        return zSlider.getValue();
    }
    
    public double getCamValue()
    {
        return camSlider.getValue() / 100.0;
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
            cam.setRotation(-90);
        }
    }
}

