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
private int targetx=20;
private int targety=20;
Map<ArrayList<Point>,JGColor> lines=new HashMap<ArrayList<Point>,JGColor>();
private Point origPoint;
JGEngine myEngine;
private double velocity=0.05;
	public Turtle() {
		
		super("Turtle", true, 20, 20, 0, "Turtle",0, 0);
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
public void setVelocity(double velo){
	velocity= velo;
}
	private int setDir(double curr, double target){
		 return (int) ((target-curr));//Math.abs(target-curr));
	}
	public void move(){
		moveToTarget();
		
	}
	public void setTarget(Point target){
		origPoint=new Point(targetx,targety);
		targetx=target.x;
		targety=target.y;
	}
	public void runPen(int thickness, JGColor color, boolean penActive){
		List<Point> loc=new ArrayList<Point>();
		loc.add(new Point((int)x,(int)y));
		loc.add(new Point(origPoint.x,origPoint.y));
		lines.put((ArrayList<Point>) loc,color);
		if(penActive&&origPoint!=null){
			for(ArrayList<Point> line:lines.keySet())
			myEngine.drawLine(line.get(0).x+10, line.get(0).y+10, line.get(1).x+10, line.get(1).y+10,thickness,lines.get(line));
		}
		
	}
	

}
