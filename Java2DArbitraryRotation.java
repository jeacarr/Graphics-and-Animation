import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
/**
 * This class shows how to use the Java 2D API in a very 
 * simple manner.
 * You can use it as a basis for other 2D programs
 */
public class Java2DArbitraryRotation extends Frame
{
	private static final long serialVersionUID = 1L;
	
	private double delta = 5 * Math.PI/180;
	private double angle = 0;

	//Constructor
		Java2DArbitraryRotation()
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
	/**
	 * Squarerotate concatenates transforms and sets it to the Graphics2D object
	 * @param x is the square top left x coordinate 
	 * @param y is the square top left y coordinate
	 * @param l is the length of the square
	 * @param cw determines if the square moves clockwise or counter-clockwise
	 * @param g is the graphic 2D object
	 */
	public void Squarerotate (int x, int y, int l, int cw, Graphics2D g)
	{
		int rotX, rotY;
		rotX = x + l / 2;
		rotY = y + l / 2;
		
		AffineTransform tran1 = new AffineTransform(), 
				rotate = new AffineTransform(), 
				tran2 = new AffineTransform();
		
		tran1.setToTranslation(rotX,rotY);
		rotate.setToRotation(cw * angle);
		tran2.setToTranslation(-rotX, -rotY);
		tran1.concatenate(rotate);
		tran1.concatenate(tran2);
		g.setTransform(tran1);
		g.drawRect(x, y, l, l);
	}
	/**
	 * Squarespin gets the Graphics2D object to rotate and draw the square
	 * @param l is the length of the square
	 * @param x1 is the square top left x coordinate for the left 
	 * @param x2 is the square top left x coordinate for the right 
	 * @param y1 is the square top left y coordinate  for the top
	 * @param y2 is the square top left y coordinate for the bottom
	 * @param g is the graphic 2D object
	 */
	public void Squarespin (int l, int x1, int x2, int y1, int y2, Graphics2D g)
	{
				Squarerotate(x1,y1,l,1, g);	
				Squarerotate(x2,y2,l,1, g);		
				Squarerotate(x1,y2,l,-1, g);		
				Squarerotate(x2,y1,l,-1, g);		
	}
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		
		Graphics2D g2d = (Graphics2D) g;
				
		// get visible and window area
		Insets in = getInsets();
		int width = getWidth(), height = getHeight(), 
				Quadwidth = (width - in.left - in.right) / 2, 
				Quadheight = (height - in.top - in.bottom) / 2;
		 
		// The length and top left points of the squares
		int Squarelength, SquareleftX, SquarerightX, SquaretopY, SquarebottomY,
		QuadDX, QuadDY;

		// determines the square length
		if(Quadwidth < Quadheight)
		{
			Squarelength = Quadwidth / 2 ;
		}
		else
		{
			Squarelength = (Quadheight * 75) / 100;
		}
		
		QuadDX = (Quadwidth - Squarelength) / 2;
		QuadDY = (Quadheight - Squarelength) /2;
		
		//centers the top left points of each square 
		SquareleftX = in.left + QuadDX;
		SquarerightX = in.left + Quadwidth + QuadDX;
		SquaretopY = in.top + QuadDY;
		SquarebottomY = in.top + Quadheight +  QuadDY ;
		
		// Uses the array and Graphics2D object to rotate squares
		Squarespin(Squarelength,SquareleftX, SquarerightX,
				SquaretopY, SquarebottomY, g2d);		
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DArbitraryRotation f = new Java2DArbitraryRotation();
		
		//Define a title for the window.
		f.setTitle("Java 2D rotaional quasi-lavalamp");
		//Definition of the window size in pixels
		f.setSize(800, 800);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

