import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class shows how to use the Java 2D API for animated motion interpolation.
 */
public class Java2DAnimationStart extends Frame
{
	// a nested class to represent the ointerpolation object
	private class IObject
	{
		private int width, height;
		private double x, y, angle; 
		
		// consturctor
		public IObject(int w, int h)
		{
			width = w;
			height = h;
		}
		
		//setting the state
		public void setState(double a, double x, double y)
		{
			angle = a;
			this.x = x;
			this.y = y;
		}
		
		//draw
		public void draw(Graphics2D g)
		{
			// draw the rectangle at it's current state 
			AffineTransform rot = new AffineTransform(), 
					translation = new AffineTransform();
			
			// set up the rotation 
			rot.setToRotation(angle);
			translation.setToTranslation(x, y);
			
			//combine 
			translation.concatenate(rot);
			
			//apply
			g.setTransform(translation);
			
			// draw the rectangle with it's center at the origin 
			g.drawRect(-width / 2, -height / 2, width, height);
			
		}
	}
	
	private static final long serialVersionUID = 1L;
	// draw into a buffered image
	private BufferedImage bufImage = null;
	private Insets insets;
	private int drawableWidth, drawableHeight, width, height;
	private static double dt;
	private IObject object; 
	//interpolation parameters 
	private double x_start, y_start, a_start, x_end, y_end, a_end,
					transitionTime, timeSoFar;
	
	//Constructor
	Java2DAnimationStart()
	{
		// frames per second (updates per second); 60 is what most real time applications,
		// such as video games, strive for
		int fps = 60;
		
		// time between updates, seconds per frame
		dt = 1.0 / fps;
		
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;

		// do this once, setup
		if(bufImage == null)
		{
			insets = getInsets();
			width = getWidth();
			drawableWidth = width - insets.left - insets.right;
			height = getHeight();
			drawableHeight = height - insets.top - insets.bottom;
			
			// set up the interpolation
			//the object to interpolate 
			object = new IObject(40, 100);
			
			//starting state
			x_start = 100;
			y_start = 100;
			// starting angle in radiance 
			a_start = 20 * Math.PI / 180;
			
			
			//end state 
			 x_end = drawableWidth - 100;
			 y_end = drawableHeight - 100;
			 a_end = 340 * Math.PI / 180 +  34 * Math.PI;
			 
			 //timing
			 transitionTime = 5.0;
			 timeSoFar = 0.0;
			 
			// last step
			bufImage = new BufferedImage(drawableWidth, drawableHeight, BufferedImage.TYPE_INT_RGB);
		}
		// display the buffer
		g2d.drawImage(bufImage, insets.left, insets.top, null);
	}
	
	// we need to override this to avoid flicker
	public void update(Graphics g)
	{
		paint(g);
	}
	
	// move the interpolation forward
	public void step()
	{
		// nothing to do
		if(bufImage == null || timeSoFar >= transitionTime)
		{
			return;
		}
		
		// drawing into this graphics context will draw into the buffer
		Graphics2D gBI = bufImage.createGraphics();
		
		// clear the background
		gBI.clearRect(0, 0, drawableWidth, drawableHeight);
		
		// do the interpolation
		double s = timeSoFar / transitionTime; // this will run from 0 to 1.0
		double i_x, i_y, i_angle;
		timeSoFar += dt;
		
		// interpolate position and angle 
		i_angle = a_start * (1-s) + a_end * s; 
		i_x = x_start * (1-s) + x_end * s;
		i_y = y_start * (1-s) + y_end * s;
		
		//
		// interpolation between starting and end angle  
		
		//same for position
		
		
		// set the new state 
		//object.setState();
		//redraw
		object.setState(i_angle, i_x, i_y);
		object.draw(gBI);
		// cause a repaint
		repaint();
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DAnimationStart f = new Java2DAnimationStart();
		
		//Define a title for the window.
		f.setTitle("Java 2D motion interpolation");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		f.setResizable(false);
		//Show the window on the screen.
		f.setVisible(true);

		// use a timer to run an animation
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				f.step();
			}
		};

		Timer timer = new Timer();

		// schedules the task to be run at regular intervals
		// dt is in seconds per frame, we need to convert to milliseconds, multiply by 1000
		// note: parameter 0 causes start without delay
		timer.scheduleAtFixedRate(task, 0, (long)(1000 * Java2DAnimationStart.dt));
	}
}