import java.awt.*;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DStart extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DStart()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(100, 200, 200, 100);

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DStart f = new Java2DStart();
		
		//Define a title for the window.
		f.setTitle("Java 2D experiments...");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

