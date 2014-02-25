package frontEnd;
import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import jgame.JGColor;
import jgame.platform.JGEngine;
//Still ne
@SuppressWarnings("serial")

public class TurtleDrawer extends JGEngine {
	 Turtle turt;
	private int xPos;
	private int yPos;
	private Point prevPos;
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
	
	setCanvasSettings(1, 1, 300, 300, null, JGColor.white, null);
}
@Override
public void initGame() {
	setFrameRate(250, 3);
	setPFSize(30,30);
	defineImage("Turtle","Turt",0,"turtle.gif","-");
	turt=new Turtle();
	turt.myEngine=this;
}
public void doFrame(){
	//turt.movePosition(Math.random()*100, Math.random()*100, 1);
	 prevPos=new Point((int)turt.x,(int)turt.y);
	moveObjects(null,0);
	
}
public void paintFrame(){
	Point currPos=new Point((int)turt.x,(int) turt.y);
	turt.runPen(2,JGColor.black,true);
}
public void refresh(){
	
}
}
