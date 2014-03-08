package TurtleStuff;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import frontEnd.Position;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;


public class Turtle extends JGObject {
	public static final int IMG_OFFSET = 12;
	private static final double THRESHOLD_VOLTAGE = 0.01;
	private static final int EDGE_OFFSET = 5;
	private static final int SCREEN_EDGE = 270;
	private boolean penActive=true;
	private int turtId;
	private int prevturtImage;
	private int turtImage=1;
	private int targetCount=0;
	private int penColorIndex=0;
	private int lineStartTarget=0;
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
	private LinkedList<Position> targetQueue=new LinkedList<Position>(); 
	private double velocity=0.02;
	public Turtle(int id) {
		super("Turtle", true, TURTLE_INIT_X, TURTLE_INIT_Y, 0, "Turtle",0, 0);
		targetQueue.add(new Position(TURTLE_INIT_X,TURTLE_INIT_Y));
		turtId=id;
		setBBox((int)x, (int)y, IMG_OFFSET, 5);
	}
	public int getPenIndex(){
		return penColorIndex;
	}
	public int getImageID(){
		if(turtImage==0){
			return prevturtImage;
		} else{
			return turtImage;
		}
	}
	public void setImageID(int ID){
		prevturtImage = turtImage;
		turtImage=ID;	
	}
	

	private void  moveToTarget(){
		double dist=Point2D.distance(x, y, targetx, targety);
		if(dist>2){
			
			xdir=setDir(x,targetx);
			ydir=setDir(y,targety);
			if(getLastX() >= SCREEN_EDGE || getLastX()<=0){
				x=SCREEN_EDGE-EDGE_OFFSET;
				xspeed=0;
			} else{
				xspeed=velocity;
			}
			if(getLastY()<=0 || getLastY()>=SCREEN_EDGE){
				y=SCREEN_EDGE-EDGE_OFFSET;
				yspeed=0;
			} else{
				yspeed=velocity;
			}
		}
		else{
			if(targetCount==targetQueue.size()){
				x=targetx;
				y=targety;
				xspeed=0;
				yspeed=0;
			}

			else{
				targetx=targetQueue.get(targetCount).xPos();
				targety=targetQueue.get(targetCount).yPos();
				targetCount++;
			}
		}
	}
		public void setEngine(JGEngine engine){
			myEngine=engine;
		}
		public double goForward(double distance){
			double rot=Math.toRadians(myRotation);
			double xOffset=Math.cos(rot)*distance;
			double yOffset=Math.sin(rot)*distance;
			if(xspeed<=THRESHOLD_VOLTAGE&&yspeed<=THRESHOLD_VOLTAGE)
				setTarget(new Position((targetQueue.get(targetQueue.size()-1).xPos()+xOffset),(targetQueue.get(targetQueue.size()-1).yPos()+yOffset)));
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
		private Position makeInBounds(Position target){
			double x=target.xPos();
			double y=target.yPos();
			x=setBounds(x);
			y=setBounds(y);
			return new Position(x,y);
		}
		private double setBounds(double coord){
			if(coord>=SCREEN_EDGE)
				return SCREEN_EDGE-EDGE_OFFSET;
			if(coord<=0)
				return 0+EDGE_OFFSET;
			else
				return coord;
		}
		public double setTarget(Position target){
			double dist=Point2D.distance(x,y,targetx,targety);
			origPosition=new Position(targetx,targety);
			targetQueue.add(makeInBounds(target));
			return dist;
		}
		public void runPen(int thickness, boolean penActive){
			if(Math.abs(xspeed)>0.01||Math.abs(yspeed)>0.01){
				List<Position> loc=new ArrayList<Position>();

				loc.add(new Position(x,y));
				loc.add(new Position(origPosition.xPos(),origPosition.yPos()));
				if(xspeed>velocity/2&&yspeed>velocity/2)
					lines.put((ArrayList<Position>) loc,drawingColor);
			}
			if(origPosition!=null){
				for(ArrayList<Position> line:lines.keySet())
					if(lines.get(line)!=null)
						myEngine.drawLine(line.get(0).xPos()+IMG_OFFSET, line.get(0).yPos()+IMG_OFFSET, line.get(1).xPos()+IMG_OFFSET, line.get(1).yPos()+IMG_OFFSET,thickness,lines.get(line));
			}
			
				

		}
		public void clearLines(){
			lines.clear();
		}
		public void setPen(int id, JGColor color){
			penColorIndex=id;
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
		private double setPosition(Position dest){
			double dist=Point2D.distance(x, y, dest.xPos(), dest.yPos());
			x=dest.xPos();
			y=dest.yPos();
			return dist;
		}
		public double reset(){
			double dist=Point2D.distance(x,y,TURTLE_INIT_X,TURTLE_INIT_Y);
			setPosition(new Position(TURTLE_INIT_X,TURTLE_INIT_Y));
			targetQueue.clear();
			targetCount=0;
			clearLines();
			setTarget(new Position(TURTLE_INIT_X,TURTLE_INIT_Y));
			myRotation=0;
			targetx=TURTLE_INIT_X;
			targety=TURTLE_INIT_Y;
			return dist;
		}
		

		public void addRotation(double addRotation){
			if(xspeed==0&&yspeed==0)
				myRotation+=addRotation;
		}
		public double setRotation(double setRotation){
			double rot=myRotation;
			myRotation=0;
			addRotation(setRotation);
			return rot-setRotation;
		}
		public boolean matchFilter(int id){
			return turtId==id;
		}
		public int getID(){
			return turtId;
		}
		public Stats getStats(){
			return new Stats(xspeed,yspeed,x,y,myRotation,targetx,targety,drawingColor);
		}
	}
