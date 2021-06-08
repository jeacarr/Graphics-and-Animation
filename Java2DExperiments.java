import java.awt.*;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DExperiments extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DExperiments()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		g2d.drawLine(100, 100, 400, 300);
		g2d.drawRect(300, 100, 200, 50);
		g2d.fillOval(400, 100, 200, 350);
		
		//polygon and polyline
		int[] x = {200, 300, 300, 100, 100}, y = {250, 300, 400, 400, 300};
		
		// change the polygon's color, the previous shapes will not be affected
		g2d.setColor(Color.orange);
		g2d.drawPolygon(x, y, x.length);
		
		//this will be hidden because of the title bar
		g2d.drawRect(0, 0, 10, 10);
		
		//draw same rectangle starting at the origin of the drawable area
		Insets in = getInsets();
		int width = getWidth(), height = getHeight();
		
		g2d.drawRect(in.left, in.top, 10, 10);
		
		//same in the right bottom
		g2d.drawRect(width - 10 - in.right - 1, height - 10 - in.bottom - 1, 10, 10);
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DExperiments f = new Java2DExperiments();
		
		//Define a title for the window.
		f.setTitle("Basis for Java 2D programs");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

