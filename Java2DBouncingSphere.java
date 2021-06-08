import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class shows how to use the Java 2D API for animated motion interpolation.
 */
public class Java2DBouncingSphere extends Frame
{
	// add a local class that implements a sphere
	private class Sphere
	{
		private double mass, posX, posY, velX, velY;
		private int radius;
		
		public Sphere(int r)
		{
			radius = r;
			mass = 1;
		}
		
		// setters
		public void setPos(double x, double y)
		{
			posX = x;
			posY = y;
		}
		
		public void setVel(double x, double y)
		{
			velX = x;
			velY = y;
		}
		
		// drawing
		public void draw(Graphics2D g)
		{
			// round and convert to int
			g.drawOval((int)Math.round(posX - radius), (int)Math.round(posY - radius),
					   2 * radius, 2 * radius);
		}
		
		// updating
		public void step(double dt)
		{
			// apply the current velocity
			posX += velX * dt;
			posY += velY * dt;
			
			// velocity changes due to gravity
			velX += gX * dt;
			velY += gY * dt;
		}
		
		// handle boundary
		public void handleBoundary()
		{
			//handle the bottom
			// below ground and moving into it
			if(posY <= radius && velY < 0)
			{
				//handle impact 
				velY *= -rest; 
				// move it back to just touching 
				posY = radius;
			}
			
			//handle the top 
			if(posY >= drawableHeight - radius && velY > 0)
			{
				//handle impact 
				velY *= -rest; 
				// move it back to just touching 
				posY = drawableHeight - radius;
			}
			
			//handle the left 
			if(posX <= radius && velX < 0)
			{
				//handle impact 
				velX *= -rest; 
				// move it back to just touching 
				posX = radius;
			}
			
			//handle the right
			if(posX >= drawableWidth - radius && velX > 0)
			{
				//handle impact 
				velX *= -rest; 
				// move it back to just touching 
				posX = drawableWidth - radius;
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	// draw into a buffered image
	private BufferedImage bufImage = null;
	private Insets insets;
	private int drawableWidth, drawableHeight, width, height;
	private static double dt;
	// have an instance variable for the sphere
	private Sphere sphere;
	private double gX, gY,rest;

	//Constructor
	Java2DBouncingSphere()
	{
		// frames per second (updates per second); 60 is what most real time applications,
		// such as video games, strive for
		int fps = 60;
		
		// time between updates, seconds per frame
		dt = 1.0 / fps;
		
		// set up animation parameters
		gX = -100;
		gY = -100;
		rest = 1.0;
		
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		int radius = 50;

		// do this once, setup
		if(bufImage == null)
		{
			insets = getInsets();
			width = getWidth();
			drawableWidth = width - insets.left - insets.right;
			height = getHeight();
			drawableHeight = height - insets.top - insets.bottom;
			
			// set up the animation
			sphere = new Sphere(radius);
			
			// move it to the top right corner
			sphere.setPos(drawableWidth - radius, drawableHeight - radius);
			//give it a velocity
			sphere.setVel(-100, 0);
			
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
		if(bufImage == null)
		{
			return;
		}
		
		// drawing into this graphics context will draw into the buffer
		Graphics2D gBI = bufImage.createGraphics();
		
		// clear the background
		gBI.clearRect(0, 0, drawableWidth, drawableHeight);
		
		// set up the coordinate transform: make y point up and change the origin
		AffineTransform trans = new AffineTransform(), flip = new AffineTransform();
		
		trans.setToTranslation(0, drawableHeight);
		flip.setToScale(1, -1);
		// combine
		trans.concatenate(flip);
		// apply
		gBI.setTransform(trans);
		
		// advance the animation
		sphere.step(dt);
		// handling the interaction with the boundary
		sphere.handleBoundary();
		sphere.draw(gBI);

		// cause a repaint
		repaint();
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DBouncingSphere f = new Java2DBouncingSphere();
		
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
		timer.scheduleAtFixedRate(task, 0, (long)(1000 * Java2DBouncingSphere.dt));
	}
}