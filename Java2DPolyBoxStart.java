import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// find and draw a bounding box for an arbitrary polygon
public class Java2DPolyBoxStart extends Frame
{
	private static final long serialVersionUID = 1L;
	
	// vertices that connect to a polygon
	private int[] xarray = { 300, 350, 400, 590, 450, 500, 450, 600, 320, 380, 250 },
				  yarray = { 150, 200, 100, 150, 200, 300, 400, 470, 500, 400, 250 };
	private Color polyColor;
	// bounding box top left, bottom right, width, and height
	private int xtop, ytop, xbottom, ybottom, width, height;

	//Constructor
	Java2DPolyBoxStart()
	{
		polyColor = Color.red;
		// enables the closing of the window
		addWindowListener(new MyFinishWindow());
		// capture the mouse down event with an event handler
		addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent evt)
					{
						// the mouse got clicked at the coordinates
						// (evt.getX(), evt.getY())
						// nothing must happen if this point lies outside the bounding box
						if(insideBox(evt.getX(), evt.getY()) == false)
						{
							return;
						}
						
						// when clicked inside the box,
						// toggle the polygon color between red and green
						if(polyColor == Color.red)
						{
							polyColor = Color.green;
						}
						else
						{
							polyColor = Color.red;
						}
						// very important call: since our code has a visible effect
						// the window has to be redrawn
						// the following call will achieve that
						repaint();
					}
				}
				);
	}
	// draw the polygon from the defined vertices, use x/yarray
	public void polyDraw(Graphics2D g)
	{
		int acc = 0;
		
		g.drawLine(xarray[xarray.length - 1], yarray[yarray.length - 1], xarray[acc], yarray[acc]);
		
		for (acc = 0; acc < xarray.length - 1; acc++)
		{
			g.drawLine( xarray[acc], yarray[acc], xarray[acc + 1], yarray[acc + 1]);
		}
	}
	
	// process the vertex coordinates to find xtop, ytop, xbottom, ybottom, width, height
	public void findBox()
	{
		xtop = xarray[0];
		ytop = yarray[0];
		xbottom = xarray[0];
		ybottom = yarray[0];
		
		for(int i = 1; i < xarray.length; i ++)
	      {
			if(xtop > xarray[i])
	         {
	           xtop = xarray[i];
	         } 
			
			if( xbottom < xarray[i])
	         {
	           xbottom = xarray[i];
	         }
	      }
		
		for(int i = 1; i < yarray.length; i ++)
	      {
	         if(ytop > yarray[i])
	         {
	           ytop = yarray[i];
	         }
	        
	         if(ybottom < yarray[i])
	         {
	           ybottom = yarray[i];
	         }
	      }
		
		width = xbottom - xtop;
		height = ybottom - ytop;
	}

	// test the point (x, y) for containment in the box defined by xtop, ytop, xbottom, ybottom
	//xbottom and ybottom are derived from the width and height
	// and return true for 'inside', false otherwise
	private boolean insideBox(int x, int y)
	{
		if (x <= xbottom && x >= xtop && y <= ybottom && y >= ytop)
			return true;
		else 
			return false;
	}
	
	public void paint(Graphics g)
	{
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;

		//Now all methods for drawing in the window should be applied to the Graphics2D
		//and not to the Graphics object any more.
		
		g2d.setColor(polyColor);
		polyDraw(g2d);
		findBox();
		g2d.setColor(Color.black);
		g2d.drawRect(xtop, ytop, width,height);
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		Java2DPolyBoxStart f = new Java2DPolyBoxStart();
		
		//Define a title for the window.
		f.setTitle("Polygon with bounding box");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

