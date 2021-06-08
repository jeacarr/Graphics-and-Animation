import java.awt.*;
import java.awt.geom.AffineTransform;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DUseTrans extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DUseTrans()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		int xtl = 100, ytl = 100, width = 200, height = 100;
		g2d.drawRect(xtl, ytl, width, height);
		
		// now apply transforms and redraw with some diff color 
		
		AffineTransform transform = new AffineTransform();
		//translation
		
		transform.setToTranslation(width, height);
		//apply that transform
		g2d.setTransform(transform);
		g2d.setColor(Color.YELLOW);
		g2d.drawRect(xtl, ytl, width, height);
		
		//rotation 
		double angle = 20 * Math.PI / 180;
		transform.setToRotation(angle);
		//apply rotation
		g2d.setTransform(transform);
		g2d.setColor(Color.ORANGE);
		g2d.drawRect(xtl, ytl, width, height);
		
		//scaling 
		double sx = 2, sy = 2;
		transform.setToScale(sx, sy);
		//apply scaling 
		g2d.setTransform(transform);
		g2d.setColor(Color.RED);
		g2d.drawRect(xtl, ytl, width, height);
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DUseTrans f = new Java2DUseTrans();
		
		//Define a title for the window.
		f.setTitle("Java 2D Translations...");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

