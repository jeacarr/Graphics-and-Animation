package paint;

import java.util.ArrayList;
import java.awt.*;

public class Freehand extends Shapes {
	
	private int [] x_free;		// Array of x points for Poly Line
	private int [] y_free;		// Array of y points for Poly Line

	/**
	 * Constructor
	 * @param Xpoints is the collected X points of the Poly Line
	 * @param Ypoints is the collected Y points of the Poly Line 
	 * @param c is the color of the Poly Line
	 * Converts the ArrayList X & Y points into an Array 
	 */
	public Freehand (ArrayList<Integer> Xpoints, ArrayList<Integer> Ypoints, Color c) 
	{
		super(Xpoints, Ypoints, false, c);
		
		int[] x = new int[X_points.size()];
		int[] y = new int[Y_points.size()];
		
		for(int i = 0; i < Xpoints.size(); i++) 
		{
		    if (Xpoints.get(i) != null)
		    {
		    	x[i] = Xpoints.get(i);
		    	y[i] = Ypoints.get(i);
		    }
		}
		
		x_free = x;
		y_free = y;
	}

	/**
	 * draw method draws a 2D Poly Line
	 */
	public void draw(Graphics2D g) 
	{
		g.setColor(shape_color);
		g.drawPolyline(x_free, y_free, X_points.size());
	}
}
