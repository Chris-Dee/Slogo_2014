import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.Graphics;

import jgame.JGColor;
import jgame.platform.JGEngine;
//Still ne
@SuppressWarnings("serial")
public class TurtleDrawer extends JGEngine {
	private int xPos;
	private int yPos;
public TurtleDrawer(int x, int y){
	int height = 800;
	double aspect = 1.0;
	initEngineComponent((int) (height * aspect), height);

	setMinimumSize(new Dimension(200,200));
	setBackground(new java.awt.Color(255,255,255));
	xPos=x;
	yPos=y;
}
public void setPositionAndPaintTurtle(int x, int y){
	xPos=x;
	yPos=y;
	repaint();
}
@Override
public void initCanvas() {
	setCanvasSettings(1, 1, 300, 300, null, JGColor.grey, null);
}
@Override
public void initGame() {
	setFrameRate(250, 3);
	
}
}
