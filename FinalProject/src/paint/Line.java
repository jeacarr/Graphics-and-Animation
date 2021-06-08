package paint;

import java.util.ArrayList;
import java.awt.*;

public class Line extends Shapes 
{
	/**
	 * Constructor 
	 * @param pointsX is the collected X points of the Line
	 * @param pointsY is the collected Y points of the Line
	 * @param c is the color of the Line
	 */
	public Line (ArrayList<Integer> pointsX, ArrayList<Integer> pointsY, Color c) 
	{
		super(pointsX, pointsY, false, c);
	}
	
	/**
	 * draw method draws a 2D Line 
	 * @override 
	 */
	public void draw(Graphics2D g) {
		
		g.setColor(shape_color);
		g.drawLine(X_points.get(0), Y_points.get(0), 
				X_points.get(1), Y_points.get(1));
	}
}
