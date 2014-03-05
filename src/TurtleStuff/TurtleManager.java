package TurtleStuff;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;

import frontEnd.Position;
import frontEnd.SlogoView;

public class TurtleManager {
	private int turtFilter=0;
	private List<Turtle> turtList=new ArrayList<Turtle>();
	private Turtle turt;
	private String chosenImage;
	TurtleDrawer myEngine;
public TurtleManager(){
	
}
public void findEngine(TurtleDrawer t){
	myEngine=t;
}
public void setFilter(int filter){
	//System.out.println(filter+ " filter");
	turtFilter=filter;
}
public void addRotations(double mag){
	for(Turtle t:getTurtlesByID()){
			t.addRotation(mag);
		}
}
public List<Turtle> getFilteredTurtles(int id){
	List<Turtle> filtTurtles=new ArrayList<Turtle>();
	for(Turtle t :turtList){
		if(t.matchFilter(id))
			filtTurtles.add(t);
	}
	return filtTurtles;
}
public void moveForward(int mag){
	System.out.println(getTurtlesByID().size());
	for(Turtle t:getTurtlesByID()){
			t.goForward(mag);
		}
}
public void refresh(){
	for(Turtle t:turtList)
	t.reset();
}

public void selectClicked(Position p){
	for(Turtle t:turtList){
		if(isClicked(p,t.getStats())){
			turt=t;
			SlogoView.viewStats().updateInfo();
		}
	}
}
public void highlightTurtle(){
	myEngine.setColor(JGColor.red);
	myEngine.drawRect(turt.x+Turtle.IMG_OFFSET, turt.y+Turtle.IMG_OFFSET, 30.0, 30.0, false, true);
}
private boolean isClicked(Position p,Stats s){
	return Point2D.distance(p.xPos(), p.yPos(),s.getPos().xPos()+10 , s.getPos().yPos()+10)<30;
	
}
public int getFilter(){
	return turtFilter;
}
public Stats displayStats(){
	return getStats(turt);
}
public void rotateImage() {
for(Turtle t:getTurtlesByID()){
	String imageString= "Turtle" + Math.random();
	Stats s = getStats(t);
	myEngine.defineImageRotated(imageString,"-",0, "Turtle", s.getRot()%360);
	t.setImage(imageString);
}
}
public Stats getStats(Turtle t){
	return t.getStats();
}
public List<Turtle> getTurtlesByID(){
	List<Turtle> filtTurts=new ArrayList<Turtle>();
	for(Turtle t:turtList)
		if(t.matchFilter(turtFilter))
			filtTurts.add(t);
	return filtTurts;
}
public void suspendTurtles(){
	for(Turtle t:getTurtlesByID())
			t.suspend();
}
public void setVelocities(double velocity) {
	for(Turtle t:getTurtlesByID())
			t.setVelocity(velocity);
	
}
public void addTurtle(Turtle t){
	if(turt==null)
		turt=t;
	turtList.add(t);
}
}
