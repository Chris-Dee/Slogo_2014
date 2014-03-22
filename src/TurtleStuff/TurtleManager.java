package TurtleStuff;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import jgame.JGColor;

import frontEnd.Position;
import frontEnd.SlogoView;

public class TurtleManager {
	List<Integer> filter=new ArrayList<Integer>();
	private List<Turtle> turtList=new ArrayList<Turtle>();
	private List<Turtle> filterList=new ArrayList<Turtle>();
	private Turtle turt;
	TurtleDrawer myEngine;
	public TurtleManager(){
	}
	public void setFilterList(List<Integer> filters){
		filterList.clear();
		for(Integer i:filters)
			for(Turtle t: turtList)
				if(t.matchFilter(i))
					filterList.add(t);
		if(filterList.size()==0){
			Turtle t=new Turtle(filters.get(0));
			addTurtle(t);
			t.setEngine(myEngine);
		}
		filter=filters;
	}
	
	public void clearTurtList(){
		turtList.clear();
	}
	public List<Turtle> getFilteredTurtles(){
		return filterList;
	}
	public void makeTurtleSet(List<Turtle> all){
		turtList=all;
		resetFilterList();
	}
	public List<Turtle> getAllTurtles(){
		return turtList;
	}
	public void findEngine(TurtleDrawer t){
		myEngine=t;
	}
	public void refresh(){
		for(Turtle t:turtList){
			t.hideTurtle();
		t.destroy();
		}
		clearTurtList();
		turt=myEngine.addnewTurtle(0);
		System.out.println(turtList.size());
		filter.clear();
		filter.add(0);
		setFilterList(filter);
		
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
	public Turtle getClickedTurtle(){
		return turt;
	}

	public Stats displayStats(){
		return getStats(turt);
	}
	public void rotateImage() {
		for(Turtle t:filterList){
			Stats s= getStats(t);
			String imageString=t.getImageName()+s.getRot();
			System.out.println(imageString);
			myEngine.defineImageRotated(imageString,"-",0, t.getImageName(), Math.toRadians(s.getRot()%360));
			t.setImage(imageString);
		}
	}
	public Stats getStats(Turtle t){
		return t.getStats();
	}

	public void setVelocities(double velocity) {
		for(Turtle t:filterList)
			t.setVelocity(velocity);
	}
	private void resetFilterList(){
		filterList.clear();
		if(filter.size()>0){
		
		for(Turtle t:turtList){
			for(Integer i:filter){
				if(t.matchFilter(i))
					filterList.add(t);
			}
			}
		}
		else {
			filterList=new ArrayList<Turtle>(turtList);
		}
		turt=filterList.get(0);
	}
	public Turtle addTurtle(Turtle t){
		if(turt==null)
			turt=t;
		for(Integer i:filter)
			if(t.matchFilter(i))
				filterList.add(t);
		if(filter.size()==0)
			filterList.add(t);
		turtList.add(t);
		return t;
	}
}
