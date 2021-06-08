import java.awt.*;
import java.awt.geom.AffineTransform;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DUseTransCombined extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DUseTransCombined()
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
		
		AffineTransform t1 = new AffineTransform(),
						t2 = new AffineTransform();
		
		//translation first, rotation next: R * T
		double tx = 400, ty = 0,
				angle = 30 * Math.PI / 180;
		
		t1.setToRotation(angle);
		t2.setToTranslation(tx, ty);
		//calculate 
		//multiplies and t1 holds the result of t2
		t1.concatenate(t2);
		
		g2d.setTransform(t1);
		g2d.setColor(Color.ORANGE);
		g2d.drawRect(xtl, ytl, width, height);
		
		// now try case 2
		t1.setToRotation(angle);
		t2.setToTranslation(tx, ty);
		
		t2.concatenate(t1);
		
		g2d.setTransform(t2);
		g2d.setColor(Color.PINK);
		g2d.drawRect(xtl, ytl, width, height);
		
	
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DUseTransCombined f = new Java2DUseTransCombined();
		
		//Define a title for the window.
		f.setTitle("Java 2D Combining Translations...");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

