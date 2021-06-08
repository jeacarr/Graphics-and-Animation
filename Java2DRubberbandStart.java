
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DRubberbandStart extends Frame
{
	private static final long serialVersionUID = 1L;
	int Xinit, Yinit, Xcur, Ycur; 
	//Constructor
	Java2DRubberbandStart()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
		// we need mouse input, so need to handle the events associated with it
		addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent evt)
					{
						Xcur = -1;
						Xinit = evt.getX();
						Yinit = evt.getY();
					}

					public void mouseReleased(MouseEvent evt)
					{
						Graphics2D g = (Graphics2D)getGraphics();
						g.setXORMode(Color.black);
						g.setColor(Color.white);
						drawRect(g);
						Xcur = - 1;
					}
				}
				);
		addMouseMotionListener(
				new MouseAdapter()
				{
					public void mouseDragged(MouseEvent evt)
					{
						Graphics2D g = (Graphics2D)getGraphics();
						
						g.setXORMode(Color.black);
						g.setColor(Color.white);
						if(Xcur > -1)
						{
							drawRect(g);	
						}
						
						Xcur = evt.getX();
						Ycur = evt.getY();
						
						drawRect(g);
						
					}
				}
				);
	}

	// draw a rectangle according to current mouse coordinates
	private void drawRect(Graphics2D g)
	{
			g.drawOval(Math.min(Xinit, Xcur),Math.min(Yinit, Ycur), Math.abs(Xcur - Xinit), Math.abs(Ycur - Yinit));
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		
		
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DRubberbandStart f = new Java2DRubberbandStart();
		
		//Define a title for the window.
		f.setTitle("Java 2D rubberband");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		// background to black
		f.setBackground(Color.black);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

