package paint;

import java.util.ArrayList;
import java.awt.*;

public class Polygon extends Shapes
{
	
	private int [] x_poly; 		// Array of x points for Polygon
	private int [] y_poly;		// Array of y points for Polygon

	/**
	 *  Constructor
	 * @param Xpoints is the collected X points of the Polygon
	 * @param Ypoints is the collected Y points of the Polygon
	 * @param c is the color of the Polygon
	 * Converts the ArrayList X & Y points into an Array 
	 */
	public Polygon (ArrayList<Integer> Xpoints, ArrayList<Integer> Ypoints, Color c) 
	{
		super(Xpoints, Ypoints, true, c);
			
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
		
		x_poly = x;
		y_poly = y;
	}
	
	/**
	 * draw method draws a 2D Polygon 
	 * determines if fill was selected to draw a filled Polygon
	 */
	public void draw(Graphics2D g) 
	{
		g.setColor(shape_color);
		
		if(getfillable() == true && filled == true)
		{
			fill(g);
		}
		
		else 
		{ 
			g.setColor(shape_color);
			g.drawPolygon(x_poly, y_poly, X_points.size());	
		}
	}
	
	/**
	 * fill method draws a 2D filled Polygon
	 */
	public void fill(Graphics2D g2d)
	{
		g2d.fillPolygon(x_poly, y_poly, X_points.size());	
	}
}
