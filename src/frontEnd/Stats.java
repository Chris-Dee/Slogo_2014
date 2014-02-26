package frontEnd;

import java.awt.Point;

import jgame.JGColor;

public class Stats {
	double x;
	double y;
	double rotation;
	int xDirect;
	int yDirect;
	JGColor penColor;
public Stats(double xPos, double yPos, double rot, int xDir, int yDir, JGColor pen){
	x=xPos;
	y=yPos;
	rotation=rot;
	xDirect=xDir;
	yDirect=yDir;
	penColor=pen;
}
public Position getPos(){
	return new Position(x,y);
}
public Point getHeading(){
	return new Point(xDirect,yDirect);
}
public double getRot(){
	return rotation;
}
public boolean penBool(){
	return penColor!=null;
}
}
