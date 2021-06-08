package paint;

import java.util.ArrayList;
import java.awt.*;

public class Rectangle extends Shapes {

	/**
	 *  Constructor 
	 * @param Xpoints is the collected X points of the Rectangle
	 * @param Ypoints is the collected Y points of the Rectangle 
	 * @param c is the color of the Rectangle
	 */
	public Rectangle (ArrayList<Integer> Xpoints, ArrayList<Integer> Ypoints, Color c) 
	{
		super(Xpoints, Ypoints, true, c);
	}
	
	/**
	 * draw method draws a 2D Rectangle
	 *  determines if fill was selected to draw a filled Rectangle
	 */
	public void draw(Graphics2D g) {
		
		g.setColor(shape_color);
		
		if(getfillable() == true && filled == true)
		{
			fill(g);
		}
		
		else 
		{
			g.drawRect(Math.min(X_points.get(0), X_points.get(1)),
				Math.min(Y_points.get(0), Y_points.get(1)),
				Math.abs(X_points.get(0) - X_points.get(1)), 
				Math.abs(Y_points.get(0) - Y_points.get(1)));	
		}
	}
	
	/**
	 * fill method draws a 2D filled Rectangle 
	 */
	public void fill(Graphics2D g2d)
	{

		g2d.fillRect(Math.min(X_points.get(0), X_points.get(1)),
					Math.min(Y_points.get(0), Y_points.get(1)),
					Math.abs(X_points.get(0) - X_points.get(1)), 
					Math.abs(Y_points.get(0) - Y_points.get(1)));	
	}
}
