import java.awt.*;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DCircleGrad extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DCircleGrad()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		// selects two colors for gradient  
		Color c1 = new Color(106,90,205),
				c2 = new Color(240,248,255);
		
		
		// get the visible window area 
		Insets in = getInsets();
		int width = getWidth() - in.left - in.right, 
			height = getHeight() - in.top - in.bottom;
		
		// circle dimensions 
		int diameter = (int)(Math.min(width, height) * .90),
				radius = diameter / 2;
		
		// starting x and y circle coordinates 
		int y = height / 2 + in.top, x = in.left + ((width - diameter) / 2);
		 
		// loop that draw the circle with lines 
		// lines are gradually changing gradient  
		for(int i = 0; i <= diameter; i++)
		{
			double t = (double)i / width;
			Color blend = new Color(
						(int)(c1.getRed() * (1-t) + c2.getRed() * t),
						(int)(c1.getGreen() * (1-t) + c2.getGreen() * t),
						(int)(c1.getBlue() * (1-t) + c2.getBlue() * t)
						);
			g2d.setColor(blend);
			
			g2d.drawLine(x + i, 
					y - (int)(Math.sqrt(Math.pow(radius, 2) - Math.pow(radius - i, 2))),
					x + i, 
					y + (int)(Math.sqrt(Math.pow(radius, 2) - Math.pow(radius - i, 2))));
		}
		
		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DCircleGrad f = new Java2DCircleGrad();
		
		//Define a title for the window.
		f.setTitle("Java 2D Circle...");
		//Definition of the window size in pixels
		f.setSize(800, 800);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

