import java.awt.*;
import java.awt.geom.AffineTransform;


/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DColorGrad extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	Java2DColorGrad()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		// vertical lines 
		int x = 100, y = 100, width = 500, height = 300;
		
		Color c1 = new Color(0,0,0),
				c2 = new Color(255,255,255);
		for(int acc = 0; acc <= width; acc++)
		{
			double t = (double)acc / width;
			Color blend = new Color(
						(int)(c1.getRed() * (1-t) + c2.getRed() * t),
						(int)(c1.getGreen() * (1-t) + c2.getGreen() * t),
						(int)(c1.getBlue() * (1-t) + c2.getBlue() * t)
						);
			g2d.setColor(blend);
			g2d.drawLine(x + acc, y, x + acc, y + height);
		}
		
		Color c3 = new Color(220,20,60),
				c4 = new Color(255,127,80);
				
		
		//for(int acc = width / 2; acc <= width; acc++)
		//{
			//double t = (double)acc / width;
			//Color blend = new Color(
						//(int)(c3.getRed() * (1-t) + c4.getRed() * t),
						//(int)(c3.getGreen() * (1-t) + c4.getGreen() * t),
						//(int)(c3.getBlue() * (1-t) + c4.getBlue() * t)
						//);
			//g2d.setColor(blend);
			//g2d.drawLine(x + acc, y, x + acc, y + height);
		//}


					
					


			

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DColorGrad f = new Java2DColorGrad();
		
		//Define a title for the window.
		f.setTitle("Java 2D experiments...");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

