import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DArbitraryRotationStart extends Frame
{
	private static final long serialVersionUID = 1L;
	
	private double delta = 10 * Math.PI/180;
	private double angle = 0;

	//Constructor
	Java2DArbitraryRotationStart()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
		addKeyListener(
				new KeyListener()
				{
					// need to override all three
					public void keyPressed(KeyEvent e)
					{
					    int keyCode = e.getKeyCode();
					    
					    switch( keyCode ) { 
					        case KeyEvent.VK_RIGHT: angle += delta;
					        	
					            break;
					        case KeyEvent.VK_LEFT: angle -= delta;
					            break;
					    }
					    // important: redraw after changing the orientation
					    repaint();
					}
					
					// need to override this even if it is not used
					public void keyReleased(KeyEvent e)	{}
					
					// need to override this even if it is not used
					public void keyTyped(KeyEvent e) {}
				}
				);
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		
	    
		Graphics2D g2d = (Graphics2D) g;
		
		Insets in = getInsets();
		int w = 300, h = 300;
		
		int width = getWidth(), height = getHeight();
		
		int x = (width - in.left - in.right - w) / 2,
				y = (height - in.bottom - in.top - h) / 2;
		
		double xr = in.left + x + (w / 2);
		double yr = in.top + y  + (h / 2);
		
		AffineTransform  t1 = new AffineTransform(),
						 t2 = new AffineTransform(),
						 t3 = new AffineTransform();
		
		g2d.drawRect(x+in.left, y+in.top, w, h);

		t1.setToTranslation(xr,yr);
		t2.setToRotation(angle);
		t3.setToTranslation(-xr, -yr);
		t1.concatenate(t2);
		t1.concatenate(t3);
		g2d.setTransform(t1);
		g2d.drawRect(x+in.left, y+in.top, w, h);

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DArbitraryRotationStart f = new Java2DArbitraryRotationStart();
		
		//Define a title for the window.
		f.setTitle("Java 2D, rotation about arbitary axis");
		//Definition of the window size in pixels
		f.setSize(800, 800);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

