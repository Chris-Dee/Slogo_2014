import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class TurtleDrawer extends JComponent {
	private int xPos;
	private int yPos;
public TurtleDrawer(int x, int y){
	super();
	setMinimumSize(new Dimension(200,200));
	setBackground(new java.awt.Color(255,255,255));
	xPos=x;
	yPos=y;
}
@Override 
public void paintComponent(Graphics g){
	super.paintComponent(g);
	System.out.println(getWidth());
	g.drawRect(xPos, yPos, 100, 100);
	System.out.println("y");
}
public void setPositionAndPaintTurtle(int x, int y){
	xPos=x;
	yPos=y;
	repaint();
}
}
