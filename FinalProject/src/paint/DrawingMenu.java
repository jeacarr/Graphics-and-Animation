package paint;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JColorChooser;

public class DrawingMenu extends Frame
{
	private static final long serialVersionUID = 1L;

	// a menu: handle exit, changing the color and the fill state
	// Items to select the type of Shape 
	private MenuBar menuBar;
	private Menu drawMenu, paintMenu;
	private MenuItem colorItem, exitItem, deleteItem, fillItem; 
	private CheckboxMenuItem lineItem, rectItem, ovalItem, 
								polyItem, freeItem, selectItem;
							
	private ArrayList<Integer> x_points;	// ArrayList to contain clicked x points
	private ArrayList<Integer> y_points;	// ArrayList to contain clicked y points
	private ArrayList<Shapes> shape;		// ArrayList to contain Shape objects	
	private Color curColor = Color.black;	// the current drawing color
	private Shapes selectShape = null;		// Contains the selected shape 

	//Constructor
	DrawingMenu()
	{
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
	
		// Instantiate the ArrayLists
		x_points = new ArrayList<Integer>();
		y_points = new ArrayList<Integer>();
		x_points = new ArrayList<Integer>(1);
		y_points = new ArrayList<Integer>(1);
		shape = new ArrayList<Shapes>();
		
		// 
		addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent evt)
					{
						// Adds points to Shapes that require two points 
						// Collect the initial point for freehand 
						if(lineItem.getState() == true || rectItem.getState() == true
								|| ovalItem.getState() == true || freeItem.getState() == true)  
						{
							x_points.add(evt.getX());
							y_points.add(evt.getY());
							
							if(x_points.size() == 2 || y_points.size() == 2)
							{
								// Creates an Instance of Line
								// Add to the Shapes ArrayList
								if(lineItem.getState() == true)
								{
									Line line = new Line(x_points, y_points, curColor);
									shape.add(line);
								}
								
								/// Creates an Instance of Rectangle 
								// Add to the Shapes ArrayList
								else if(rectItem.getState() == true)
								{
									Rectangle rectangle = new Rectangle(x_points, y_points, curColor);
									shape.add(rectangle);
								}
								
								// Creates an Instance of Oval
								// Add to the Shapes ArrayList
								else if(ovalItem.getState() == true) 
								{
									Oval oval = new Oval(x_points, y_points, curColor);
									shape.add(oval);
								}	
								x_points.clear();
								y_points.clear();
							}
						}
						
						// Collect points for Polygon 
						// Creates an Instance of Polygon
						// Add to the Shapes ArrayList
						else if(polyItem.getState() == true) 
						{
							x_points.add(evt.getX());
							y_points.add(evt.getY());
								
							if(x_points.size() >= 3 && evt.getX() == x_points.get(x_points.size() - 2))
							{
								x_points.remove(x_points.size() - 1);
								y_points.remove(y_points.size() -1);
								
								Polygon poly = new Polygon(x_points, y_points, curColor);
								shape.add(poly);
									
								x_points.clear();
								y_points.clear();
							}	
						}
						
						// Collects a mouse click to determine 
						// if it is in the bounding box of a shape
						else if(selectItem.getState() == true)
						{
							x_points.add(evt.getX());
							y_points.add(evt.getY());
								
							if(x_points.size() == 1 || y_points.size() == 1)
							{
								// Loop that runs through all shapes in the 
								// ArrayList to determine if a shape is selected
								for(int i = 0; i < shape.size(); i++)
								{
									if(shape.get(i).test(x_points, y_points) == true)
									{			
										selectShape = shape.get(i);
										break;
									}
									
									else 
									{		
										selectShape = null;
									}
								}
									x_points.clear();
									y_points.clear();	
							}	
						}	
						

						repaint();	
					}
					
					// Creates an Instance of Freehand
					// Add to the Shapes ArrayList
					public void mouseReleased(MouseEvent evt)
					{
									
						if(freeItem.getState() == true && x_points.size() != 0)
						{
							Freehand free = new Freehand(x_points, y_points, curColor);
							shape.add(free);
									
							x_points.clear();
							y_points.clear();
							repaint();
						}
					}
					
				});
		// Add dragged points for freehand 
		addMouseMotionListener(
				new MouseAdapter()
				{
					public void mouseDragged(MouseEvent evt)
					{
						if(freeItem.getState() == true)
						{
							x_points.add(evt.getX());
							y_points.add(evt.getY());
						}	
					}
				}
				);	
		createMenu();
	}

	private void createMenu()
	{
		// build a menu
		menuBar = new MenuBar();
		
		// main menu bar
		setMenuBar(menuBar);
		
		// a menu
		drawMenu = new Menu("Draw");
		menuBar.add(drawMenu); 
		
		// Line Menu Item Check box
		lineItem = new CheckboxMenuItem("Line", false);
		drawMenu.add(lineItem);
		lineItem.addItemListener(
				// Clear previous points and selected objects
				// Other shape types set to false
				// Disables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						rectItem.setState(false);
						ovalItem.setState(false);
						polyItem.setState(false);
						freeItem.setState(false);
						selectItem.setState(false);
						selectShape = null;
						fillItem.setEnabled(false);
						deleteItem.setEnabled(false);
					}
				});
		
		// Rectangle Menu Item Check box
		rectItem = new CheckboxMenuItem("Rectangle",false);
		drawMenu.add(rectItem);
		rectItem.addItemListener(
				// Clear previous points and selected objects
				// Other shape types set to false
				// Disables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						lineItem.setState(false);
						ovalItem.setState(false);
						polyItem.setState(false);
						freeItem.setState(false);
						selectItem.setState(false);
						selectShape = null;
						fillItem.setEnabled(false);
						deleteItem.setEnabled(false);
					}
				});
		
		// Oval Menu Item Check box
		ovalItem = new CheckboxMenuItem("Oval",false);
		drawMenu.add(ovalItem);
		ovalItem.addItemListener(
				// Clear previous points and selected objects
				// Other shape types set to false
				// Disables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						lineItem.setState(false);
						rectItem.setState(false);
						polyItem.setState(false);
						freeItem.setState(false);
						selectItem.setState(false);
						selectShape = null;
						fillItem.setEnabled(false);
						deleteItem.setEnabled(false);
					}
				});
		
		// Polygon Menu Item Check box
		polyItem = new CheckboxMenuItem("Polygon",false);
		drawMenu.add(polyItem);
		polyItem.addItemListener(
				// Clear previous points and selected objects
				// Other shape types set to false
				// Disables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						lineItem.setState(false);
						rectItem.setState(false);
						ovalItem.setState(false);
						freeItem.setState(false);
						selectItem.setState(false);
						selectShape = null;
						fillItem.setEnabled(false);
						deleteItem.setEnabled(false);
					}
				});
		
		// Freehand Menu Item Check Box
		freeItem = new CheckboxMenuItem("Freehand",false);
		drawMenu.add(freeItem);
		freeItem.addItemListener(
				// Clear previous points and selected objects
				// Other shape types set to false
				// Disables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						lineItem.setState(false);
						rectItem.setState(false);
						ovalItem.setState(false);
						polyItem.setState(false);
						selectItem.setState(false);
						selectShape = null;
						fillItem.setEnabled(false);
						deleteItem.setEnabled(false);
					}
				});
		// Select Menu Item Check box
		selectItem = new CheckboxMenuItem("Select",false);
		drawMenu.add(selectItem);
		selectItem.addItemListener(
				// Clear previous points 
				// Shape types set to false
				// Enables fill and deleting an object
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						x_points.clear();
						y_points.clear();
						lineItem.setState(false);
						rectItem.setState(false);
						ovalItem.setState(false);
						polyItem.setState(false);
						freeItem.setState(false);	
						fillItem.setEnabled(true);
						deleteItem.setEnabled(true);
						
						if(selectShape != null && selectShape.getfillable() == false)
						{
							fillItem.setEnabled(false);
						}
					}
				});
		
		paintMenu = new Menu("Paint");
		menuBar.add(paintMenu);
		
		// a menu item through which we allow changing the color
		colorItem = new MenuItem("Color");
		paintMenu.add(colorItem);
		// color dialog handler
		colorItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						
						// bring up a color dialog with the current color selected
						Color c = JColorChooser.showDialog(null, "Select drawing color", curColor);

						curColor = c;
						// we changed the color and want to see the effect

						if(selectShape != null)
						{
							selectShape.shape_color = curColor;
						}
						
						repaint();
					}
				}
				);
	
		// Fill Menu Item
		fillItem = new MenuItem("Fill shape");
		paintMenu.add(fillItem);
		fillItem.addActionListener(
				// toggle between filling / outline
				// of the selected shapes
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						selectShape.shape_color = curColor;
						selectShape.setFill(!selectShape.filled);	
						repaint();
					}
				}
				);
		
		// Delete Menu Item
		deleteItem = new MenuItem("Delete");
		paintMenu.add(deleteItem);
		deleteItem.addActionListener(
				new ActionListener()
				{
					// Deletes selected shape 
					public void actionPerformed(ActionEvent evt)
					{
						
						if(selectShape != null)
						{
							shape.remove(selectShape);
							selectShape = null;

						}
						
							
						
						repaint();
					}
				}
				);

		// exit
		exitItem = new MenuItem("Exit");
		drawMenu.add(exitItem);
		// exit handler
		exitItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						// the parameter of exit() can indicate an error code
						// zero means the program exited without error
						System.exit(0);
					}
				}
				);
	}

	// main drawing routine
	public void paint(Graphics g)
	{	
		//In order to use Java 2D, it is necessary to cast the Graphics object
		//into a Graphics2D object.
		Graphics2D g2d = (Graphics2D) g;
		
		// Draws mouse click points as crosses
		for(int i = 0; i < x_points.size(); i++)
		{
			g2d.drawLine(x_points.get(i) - 2, y_points.get(i), 
					x_points.get(i) + 2,  y_points.get(i));
			
			g2d.drawLine(x_points.get(i), y_points.get(i) - 2, 
					x_points.get(i),  y_points.get(i) + 2);
		}
		
		// Draws the shapes in the ArrayList shape
		for(int i = 0; i < shape.size(); i++)
		{
			shape.get(i).draw(g2d);
		}
		
		// Draws the bounding of the selected shape 
		if(selectShape != null)
		{
			selectShape.boundingBox(g2d);
		}		
		
	}
		
	public static void main(String[] argv)
	{
		//Generate the window.
		DrawingMenu f = new DrawingMenu();
		//Define a title for the window.
		f.setTitle("Draw a variety of Shapes!");      
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}