package TurtleStuff;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import jgame.platform.JGEngine;

import frontEnd.Position;

public class TurtleManager {
	private int turtFilter=0;
	private List<Turtle> turtList=new ArrayList<Turtle>();
	private List<Turtle> filterList=new ArrayList<Turtle>();
	private Turtle turt;
	private String chosenImage;
	TurtleDrawer myEngine;
public TurtleManager(){}

public void setFilter(int filter){
	//System.out.println(filter+ " filter");
	turtFilter=filter;
}
public void addRotations(double mag){
	for(Turtle t:getTurtlesByID(turtFilter)){
			t.addRotation(mag);
		}
}
public void moveForward(int mag){
	for(Turtle t:getTurtlesByID(turtFilter)){
			t.goForward(mag);
		}
}
public void selectClicked(Position p){
	for(Turtle t:getTurtlesByID(turtFilter)){
		
		if(isClicked(p,t.getStats())){
			turt=t;
			System.out.println(turt.getStats().getPos().xPos());
		}
	}
}
private boolean isClicked(Position p,Stats s){
	return Point2D.distance(p.xPos(), p.yPos(),s.getPos().xPos()+10 , s.getPos().yPos()+10)<10;
	
}
public Stats displayStats(){
	return getStats(turt);
}
public void rotateImage() {
for(Turtle t:getTurtlesByID(turtFilter)){
	String imageString= "Turtle" + Math.random();
	Stats s = getStats(t);
	myEngine.defineImageRotated(imageString,"-",0, "Turtle", s.getRot()%360);
	t.setImage(imageString);
}

}
public Stats getStats(Turtle t){
	return t.getStats();
}
public List<Turtle> getTurtlesByID(int id){
	List<Turtle> filtTurts=new ArrayList<Turtle>();
	for(Turtle t:turtList)
		if(t.matchFilter(id))
			filtTurts.add(t);
	return filtTurts;
}
public void suspendTurtles(){
	for(Turtle t:getTurtlesByID(turtFilter))
			t.suspend();
}
public void setVelocities(double velocity) {
	for(Turtle t:getTurtlesByID(turtFilter))
			t.setVelocity(velocity);
	
}
}
