package TurtleStuff;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import OptionsPanel.NewFilterTurtle;

import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;

import frontEnd.Position;
import frontEnd.SlogoView;

public class TurtleManager {
	private int[] turtFilter;
	private List<Turtle> turtList=new ArrayList<Turtle>();
	private List<Turtle> filterList=new ArrayList<Turtle>();
	private Turtle turt;
	private String chosenImage;
	TurtleDrawer myEngine;
public TurtleManager(){
}
public void setFilterList(List<Integer> filters){
	filterList.clear();
	for(Integer i:filters)
		for(Turtle t: turtList)
			if(t.matchFilter(i))
				filterList.add(t);
	setFiltertext();
	
}
public List<Turtle> getFilteredTurtles(){
	if(filterList.size()!=0)
	return filterList;
	return turtList;
}
public void findEngine(TurtleDrawer t){
	myEngine=t;
}
public void setFiltertext(){
	if(turtFilter!=null)
	NewFilterTurtle.setFilterText(turtFilter);
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
public void highlightSelectedTurtles(){
	myEngine.setColor(JGColor.blue);
	for(Turtle t:filterList)
		myEngine.drawRect(t.x+Turtle.IMG_OFFSET, t.y+Turtle.IMG_OFFSET, 30.0, 30.0, false, true);
}
public void highlightTurtle(){
	myEngine.setColor(JGColor.red);
	myEngine.drawRect(turt.x+Turtle.IMG_OFFSET, turt.y+Turtle.IMG_OFFSET, 30.0, 30.0, false, true);
}
private boolean isClicked(Position p,Stats s){
	return Point2D.distance(p.xPos(), p.yPos(),s.getPos().xPos()+10 , s.getPos().yPos()+10)<30;
	
}

public Stats displayStats(){
	return getStats(turt);
}
public void rotateImage() {
for(Turtle t:filterList){
	String imageString= "Turtle" + Math.random();
	Stats s = getStats(t);
	myEngine.defineImageRotated(imageString,"-",0, "Turtle", s.getRot()%360);
	t.setImage(imageString);
}
}
public Stats getStats(Turtle t){
	return t.getStats();
}
public void suspendTurtles(){
	for(Turtle t:filterList)
			t.suspend();
}
public void setVelocities(double velocity) {
	for(Turtle t:filterList)
			t.setVelocity(velocity);
	
}
public void addTurtle(Turtle t){
	if(turt==null)
		turt=t;
	turtList.add(t);
	
}
}
