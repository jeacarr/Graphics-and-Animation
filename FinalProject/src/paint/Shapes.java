package paint;

import java.awt.*;
import java.util.ArrayList;

public class Shapes 
{
	protected ArrayList<Integer> X_points; 	// Holds x points of the shape
	protected ArrayList<Integer> Y_points; 	// Holds y points of the shape
	protected boolean filled; 				// Determines if a shape can be filled
	
	protected Color shape_color; 			// Holds the current shape color
	private boolean fillable;				// Determines if a shape can be filled
	
	private int width;						// Holds the width of the bounding box 
	private int height;						// Holds the height of the bounding box
	private int X_box;						// Holds the x top point of the bounding box
	private int Y_box;						// Holds the y top point of the bounding box
	
	/**
	 * Constructor
	 * @param pointsX
	 * @param pointsY
	 * @param fill
	 * @param c
	 */
	public Shapes(ArrayList<Integer> pointsX,ArrayList<Integer> pointsY,
			boolean fill, Color c) 
	{
		X_points = new ArrayList<Integer>();
		Y_points = new ArrayList<Integer>();
		fillable = fill;
		filled = false;
		shape_color = c;
	
		for(int i = 0; i < pointsX.size(); i++)
		{
			X_points.add(pointsX.get(i));
			Y_points.add(pointsY.get(i));
		}
		
		findBox();
	}
	
	/**
	 * draw method draws a 2D shape 
	 * @param g2d
	 */
	public void draw(Graphics2D g2d)
	{
		
	}
	
	/**
	 * fill method draws a filled 2D shape
	 * @param g2d
	 */
	public void fill( Graphics2D g2d)
	{
		
	}
	
	public void setFill(boolean fill)
	{
		filled = fill;
	}
	
	/**
	 * getfillable method
	 * @return if a shape can be filled
	 */
	public boolean getfillable()
	{
		return fillable; 
	}
	
	/**
	 * findbox method 
	 * calculates the bounding box of a shape using the 
	 * width, height, X top, and Y top
	 * determines the max & min of the X & Y points
	 */
	public void findBox()
	{
		int xtop = X_points.get(0), ytop = Y_points.get(0), 
				xbottom = X_points.get(0), ybottom = Y_points.get(0);
		
		for(int i = 1; i < X_points.size(); i ++)
	      {
			if(xtop > X_points.get(i))
	         {
	           xtop = X_points.get(i);
	         } 
			
			if( xbottom < X_points.get(i))
	         {
	           xbottom = X_points.get(i);
	         }
	      }
		
		for(int i = 1; i < Y_points.size(); i ++)
	      {
	         if(ytop > Y_points.get(i))
	         {
	           ytop = Y_points.get(i);
	         }
	        
	         if(ybottom < Y_points.get(i))
	         {
	           ybottom = Y_points.get(i);
	         }
	      }
		
		width = xbottom - xtop;
		height = ybottom - ytop;
		X_box = xtop;
		Y_box = ytop;
	}
	
	/**
	 * boundingBox method draws a visible bounding box 
	 * @param g draw the rectangle for the bounding box
	 */
	public void boundingBox(Graphics2D g)
	{
		g.setColor(Color.red);	
		g.drawRect(X_box, Y_box, width, height);;
	}
	
	/**
	 * test method 
	 * Determines if a point is within the bounding box of a shape 
	 * @param x is the clicked x point 
	 * @param y is the clicked y point
	 * @return if the point is in the bounding box 
	 */
	public boolean test(ArrayList<Integer> x, ArrayList<Integer> y) 
	{
		if(x.get(0) <= X_box + width && x.get(0) >= X_box
				&& y.get(0) <= Y_box + height && y.get(0) >= Y_box)
		{
			return true;
		}
	
		else return false;
	}
}
