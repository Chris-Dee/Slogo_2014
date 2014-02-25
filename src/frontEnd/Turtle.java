package frontEnd;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;


public class Turtle extends JGObject {
	public static final int TURTLE_INIT_X=100;
	public static final int TURTLE_INIT_Y=100;
private int targetx=TURTLE_INIT_X;
private int targety=TURTLE_INIT_Y;
Map<ArrayList<Point>,JGColor> lines=new HashMap<ArrayList<Point>,JGColor>();
private Point origPoint;
private double myRotation=90;
private JGColor drawingColor=JGColor.black;
private JGColor penColor=JGColor.black;
JGEngine myEngine;
private double velocity=0.05;
	public Turtle() {
		
		super("Turtle", true, TURTLE_INIT_X, TURTLE_INIT_Y, 0, "Turtle",0, 0);
		// TODO Auto-generated constructor stub
	}
private void  moveToTarget(){
	double dist=Point2D.distance(x, y, targetx, targety);
	if(dist>1){
	xdir=setDir(x,targetx);
	ydir=setDir(y,targety);
	xspeed=velocity;
	yspeed=velocity;
	}
	else{
		xspeed=0;
		yspeed=0;
	}
}
public void goForward(int offset){
	double rot=Math.toRadians(myRotation);
	double xOffset=Math.cos(rot)*offset;
	double yOffset=Math.sin(rot)*offset;
	setTarget(new Point((int)(x+xOffset),(int)(y+yOffset)));
}
public void setVelocity(double velo){
	velocity= velo;
}
	private int setDir(double curr, double target){
		 return (int) ((target-curr));
	}
	public void move(){
		moveToTarget();
		
	}
	public void setTarget(Point target){
		origPoint=new Point(targetx,targety);
		targetx=target.x;
		targety=target.y;
	}
	public void runPen(int thickness, boolean penActive){
		if(Math.abs(xdir)>0||Math.abs(ydir)>0){
		List<Point> loc=new ArrayList<Point>();
		loc.add(new Point((int)x,(int)y));
		loc.add(new Point(origPoint.x,origPoint.y));
		lines.put((ArrayList<Point>) loc,drawingColor);
		}
		if(origPoint!=null){
			for(ArrayList<Point> line:lines.keySet())
				if(lines.get(line)!=null)
			myEngine.drawLine(line.get(0).x+10, line.get(0).y+10, line.get(1).x+10, line.get(1).y+10,thickness,lines.get(line));
		}
		
	}
	public void clearLines(){
		lines.clear();
	}
	public void setPen(JGColor color){
		penColor=color;
		drawingColor=color;
	}
	public void raisePen(){
		drawingColor=null;
	}
	public void lowerPen(){
		drawingColor=penColor;
	}
	public void addRotation(double addRotation){
		myRotation+=addRotation;
	}
	public int getPosx(){
		return targetx;
	}
	
	public int getPosy(){
		return targety;
	}

}
