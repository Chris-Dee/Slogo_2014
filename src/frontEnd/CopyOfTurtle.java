package frontEnd;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import TurtleStuff.Stats;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;


public class CopyOfTurtle extends JGObject {
	private boolean penActive=true;
	public static final int TURTLE_INIT_X=100;
	public static final int TURTLE_INIT_Y=100;
	private double targetx=TURTLE_INIT_X;
	private double targety=TURTLE_INIT_Y;
	Map<ArrayList<Position>,JGColor> lines=new HashMap<ArrayList<Position>,JGColor>();
	private Position origPosition=new Position(TURTLE_INIT_X,TURTLE_INIT_Y);
	private double myRotation=0;
	private JGColor drawingColor=JGColor.black;
	private JGColor penColor=JGColor.black;
	private JGEngine myEngine;
	private double velocity=0.02;
	public CopyOfTurtle() {
		super("Turtle", true, TURTLE_INIT_X, TURTLE_INIT_Y, 0, "Turtle",0, 0);
	}
	private void  moveToTarget(){
		double dist=Point2D.distance(x, y, targetx, targety);
		if(dist>2){
			xdir=setDir(x,targetx);
			ydir=setDir(y,targety);
			xspeed=velocity;
			yspeed=velocity;
		}
		else{
			x=targetx;
			y=targety;
			xspeed=0;
			yspeed=0;

		}
	}
public void setEngine(JGEngine engine){
	myEngine=engine;
}
	public double goForward(double distance){
		double rot=Math.toRadians(myRotation);
		double xOffset=Math.cos(rot)*distance;
		double yOffset=Math.sin(rot)*distance;
		if(xspeed==0&&yspeed==0)
			System.out.println("added to queue");
		setTarget(new Position((targetx+xOffset),(targety+yOffset)));
		return distance;
	}
	public double setVelocity(double velo){
		velocity= velo;
		return velo;
	}
	private int setDir(double curr, double target){
		return (int) ((target-curr));
	}
	public void move(){
		moveToTarget();

	}
	public double setTarget(Position target){
		
		double dist=Point2D.distance(x,y,targetx,targety);
		origPosition=new Position(targetx,targety);
		targetx=target.xPos();
		targety=target.yPos();
		System.out.println(target.xPos()+"  "+ target.yPos());
		return dist;
	}
	public void runPen(int thickness, boolean penActive){
		if(Math.abs(xdir)>0||Math.abs(ydir)>0){
			List<Position> loc=new ArrayList<Position>();
			
			loc.add(new Position(x,y));
			
			loc.add(new Position(origPosition.xPos(),origPosition.yPos()));
			if(xspeed>velocity/2&&yspeed>velocity/2)
				lines.put((ArrayList<Position>) loc,drawingColor);

			}
		if(origPosition!=null){
			for(ArrayList<Position> line:lines.keySet())
				if(lines.get(line)!=null)
					myEngine.drawLine(line.get(0).xPos()+10, line.get(0).yPos()+10, line.get(1).xPos()+10, line.get(1).yPos()+10,thickness,lines.get(line));
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
		penActive=false;
		drawingColor=null;
	}
	public void lowerPen(){
		penActive=true;
		drawingColor=penColor;
	}
	//returns distance traveled
	public double setPosition(Position dest){
		double dist=Point2D.distance(x, y, dest.xPos(), dest.yPos());
		x=dest.xPos();
		y=dest.yPos();
		return dist;
	}
	public void reset(){
		setPosition(new Position(TURTLE_INIT_X,TURTLE_INIT_Y));
		clearLines();
		setTarget(new Position(TURTLE_INIT_X,TURTLE_INIT_Y));
	}
	public void addRotation(double addRotation){
		if(xspeed==0&&yspeed==0)
		myRotation+=addRotation;
	}
	
	public double getangle(){
		return myRotation;
	}
	public double setRotation(double setRotation){
		double rot=myRotation;
		myRotation=0;
		addRotation(setRotation);
		return rot-setRotation;
	}
	public Stats getStats(){
		return new Stats(xspeed, yspeed, targetx,targety,myRotation,xdir,ydir,drawingColor);
	}
}




