import java.awt.*;


/**
 * This class creates a grid and then fills the grid
 * layout with lines
 */
public class GridFilling extends Frame
{
	private static final long serialVersionUID = 1L;

	//Constructor
	GridFilling()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	}
	
	public void paint(Graphics g)
	{
		//Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		//Insets object
		Insets in = getInsets();
		
		//RECLENGTH is the distance apart of lines
		final int RECLENGTH = 100;
		
		/** 
		* width is screen width
		* height is screen height
		* acc is the accumulator of the loops
		* ndraw is the not drawable area size 
		*/
		int width = getWidth(), height = getHeight(), acc = 0, ndraw = 1;
		
		//Vertical grid lines
		g2d.drawLine(width - in.right - RECLENGTH - ndraw, in.top, 
				width - in.right - RECLENGTH - ndraw, height - in.bottom);
		
		g2d.drawLine(in.left + RECLENGTH, in.top,
				in.left + RECLENGTH, height - in.bottom);
		
		//Horizontal grid lines
		g2d.drawLine(in.left, height - in.bottom - RECLENGTH - ndraw,
				 width - in.right,height - in.bottom - RECLENGTH - ndraw);
		
		g2d.drawLine(in.left, in.top + RECLENGTH,
				 width - in.right, in.top + RECLENGTH);	
		 
		 //Changes graphic2D object color
		 g2d.setColor(Color.yellow);
		 
		 //Fills the corners of the grid with lines
		 do
		 {
			 g2d.drawLine(in.left, height - in.bottom - acc,
					 in.left + RECLENGTH - ndraw, height - in.bottom - acc); 
			 
			 g2d.drawLine(in.left, in.top + RECLENGTH - ndraw - acc,
					 in.left + RECLENGTH - ndraw,in.top + RECLENGTH - ndraw - acc); 
			 
			 g2d.drawLine(width - in.right - RECLENGTH, height - in.bottom - acc,
					 width - in.right, height - in.bottom - acc); 
			
			 g2d.drawLine(width - in.right - RECLENGTH, in.top + RECLENGTH - ndraw - acc,
					 width - in.right, in.top + RECLENGTH - ndraw - acc); 
			 acc++;
		 }while(acc <= RECLENGTH);
		 
		 //Resets accumulator for center grid space
		 acc=2; 
		 
		 //Fills the center grid space with lines 
		 do
		 {
			 g2d.drawLine(in.left + RECLENGTH + ndraw, height - in.bottom - RECLENGTH - acc,
					 width - in.right - RECLENGTH - ndraw - ndraw, height - in.bottom - RECLENGTH - acc);
			 acc++;
		 }while(acc <= height - RECLENGTH - RECLENGTH - in.bottom - in.top - ndraw);	 
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		GridFilling f = new GridFilling();
		
		//Define a title for the window.
		f.setTitle("Filling with Loops");
		//Definition of the window size in pixels
		f.setSize(600, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

