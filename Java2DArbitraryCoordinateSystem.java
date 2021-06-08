import java.awt.*;
import java.awt.geom.AffineTransform;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DArbitraryCoordinateSystem extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DArbitraryCoordinateSystem()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		int[] x = {0, 200, 200, 100, 0},
				y = {0,0,200, 300, 200};
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		
		g2d.setColor(Color.RED);
		g2d.drawPolygon(x, y, x.length);
		
		AffineTransform  t1 = new AffineTransform(),
				 t2 = new AffineTransform();
		Insets in = getInsets();
		int width = getWidth(), height = getHeight();
		
		int xc = in.left + (width - in.right - in.left) / 2,
				yc = in.top + (height - in.top - in.bottom) / 2;
		
		g2d.setColor(Color.BLUE);
		g2d.drawLine(xc , 0, xc, height);
		g2d.drawLine(0, yc, width, yc);
		
		
		
		t1.setToScale(1, -1);
		t2.setToTranslation(xc, yc);
		t2.concatenate(t1);
		
		g2d.setTransform(t2);
		g2d.setColor(Color.ORANGE);
		g2d.drawPolygon(x, y, x.length);
		
		int wr = 200, hr = 100, xr = -100, 
			yr = -50;
		g2d.setColor(Color.GRAY);
		g2d.drawRect(xr, yr, wr, hr);
		
		

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DArbitraryCoordinateSystem f = new Java2DArbitraryCoordinateSystem();
		
		//Define a title for the window.
		f.setTitle("Java 2D experiments...");
		//Definition of the window size in pixels
		f.setSize(800, 800);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

