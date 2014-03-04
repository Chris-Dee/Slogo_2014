package frontEnd;

import java.awt.Point;

import jgame.JGColor;

public class Stats {
	double x;
	double y;
	double rotation;
	double targetx;
	double targety;
	JGColor penColor;
	double velox;
	double veloy;
public Stats(double vx, double vy,double xPos, double yPos, double rot, double xTarg, double yTarg, JGColor pen){
	x=xPos;
	y=yPos;
	rotation=rot;
	targetx=xTarg;
	targety=yTarg;
	penColor=pen;
	velox=vx;
	veloy=vy;
	
	
}
public Position getSpeed(){
	return new Position(velox,veloy);
}
public Position getPos(){
	return new Position(x,y);
}
public Position getTarget(){
	return new Position(targetx,targety);
}
public double getRot(){
	return rotation;
}
public boolean penBool(){
	return penColor!=null;
}
}
