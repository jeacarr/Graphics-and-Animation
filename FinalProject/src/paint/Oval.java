package paint;

import java.util.ArrayList;
import java.awt.*;

public class Oval extends Shapes{

	/**
	 *  Constructor 
	 * @param Xpoints is the collected X points of the Oval
	 * @param Ypoints is the collected Y points of the Oval
	 * @param c is the color of the Oval
	 */
	public Oval (ArrayList<Integer> Xpoints, ArrayList<Integer> Ypoints, Color c) 
	{
		super(Xpoints, Ypoints, true, c);
	}
	
	/**
	 *  draw method draws a 2D Oval
	 *  determines if fill was selected to draw a filled Oval
	 */
	public void draw(Graphics2D g) {
		
		g.setColor(shape_color);
		
		if(getfillable() == true && filled == true)
		{
			fill(g);
		}
		else
		{
			g.drawOval(Math.min(X_points.get(0), X_points.get(1)),
					Math.min(Y_points.get(0), Y_points.get(1)),
					Math.abs(X_points.get(0) - X_points.get(1)), 
					Math.abs(Y_points.get(0) - Y_points.get(1)));	
		}
	}
	
	/**
	 * fill method draws a 2D filled Oval
	 */
	public void fill(Graphics2D g2d)
	{
			g2d.fillOval(Math.min(X_points.get(0), X_points.get(1)),
					Math.min(Y_points.get(0), Y_points.get(1)),
					Math.abs(X_points.get(0) - X_points.get(1)), 
					Math.abs(Y_points.get(0) - Y_points.get(1)));	
	}
}
