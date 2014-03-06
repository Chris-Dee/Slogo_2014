package frontEnd;
import javax.swing.JComponent;

import TurtleStuff.Stats;
import TurtleStuff.Turtle;
import backEnd.SlogoModel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;
//Still ne
@SuppressWarnings("serial")

public class TurtleDrawer extends JGEngine {
	private Turtle turt;
	private String chosenImage;
	private int turtFilter=0;
	private List<Turtle> turtList=new ArrayList<Turtle>();
	//tempList
/*
List<turtles> get turtswithFilter()

 */
	public TurtleDrawer(){
		int height = 900;
		double aspect = 0.5;
		initEngineComponent((int) (height * aspect), height);

		//setMinimumSize(new Dimension(200,200));
		setBackground(new java.awt.Color(255,255,255));

	}
	public Turtle getTurtle(){
		return turt;
	}
	public void setPositionAndPaintTurtle(int x, int y){
		repaint();
	}
	@Override
	public void initCanvas() {
		setCanvasSettings(1, 1, 300, 300, null, JGColor.white, null);
	}
	@Override
	public void initGame() {
		setFrameRate(250, 3);
		setPFSize(30,30);
		defineImage("Turtle","Turt",0,"turtle.gif","-");
		turt=new Turtle(0);
		turtList.add(turt);
		turt.setEngine(this);
	}
	public void doFrame(){
		moveObjects();
		checkClicked();
		SlogoView.viewStats().updateInfo();
	}
	public void paintFrame(){
		for(Turtle t:turtList){
			//System.out.println(turtFilter+"  "+t.turtId);
			if(t.matchFilter(turtFilter))
				t.runPen(2,true);
		}
		checkColorKeys();
	}
	public boolean checkKey(char ch){
		if(getLastKey()+32==(int)ch||(getLastKey()<60&&getLastKey()==ch)){
			return true;}
		return false;
	}
	public void checkColorKeys(){
		for(Turtle t:turtList)
			if(t.matchFilter(turtFilter)){
				if(checkKey('r')){
					t.setPen(10,JGColor.red);
				}
				if(checkKey('g')){
					t.setPen(10,JGColor.green);
				}
				if(checkKey('b')){
					t.setPen(10,JGColor.blue);
				}
				if(checkKey('o')){
					t.setPen(10,JGColor.orange);
				}
				if(checkKey('r')){
					t.raisePen();
				}
				if(checkKey('m')){
					t.lowerPen();
				}
			}
		clearLastKey();

	}
	public void refresh(){
		for(Turtle t:turtList)
		t.reset();
	}

	public void newTurtle(String imageFile) {
		Position newPos=turt.getStats().getPos();
		double targetx = newPos.xPos();
		double targety = newPos.yPos();	
		defineImage("Turtle","Turt",0,imageFile,"-");
		chosenImage = "Turtle";
		turt.setImage(chosenImage);
		turt.setPos(targetx, targety);
		turt.setEngine(this);

	}

	public void addnewTurtle(int id){
		defineImage("Turtle","Turt",0,"turtle.gif","-");
		turt=new Turtle(id);
		turtList.add(turt);
		//System.out.println(turtList.size());
		turt.setEngine(this);
	}

public void moveForward(int mag){
	for(Turtle t:turtList)
		if(t.matchFilter(turtFilter)){
			t.goForward(mag);
		}
}
private void checkClicked(){
	if(getMouseButton(1)){
		clearMouseButton(1);
		JGPoint p=getMousePos();
		selectClicked(new Position(p.x,p.y));
	}
}
public void selectClicked(Position p){
	for(Turtle t:turtList){
		
		if(isClicked(p,t.getStats())){
			turt=t;
			System.out.println(turt.getStats().getPos().xPos());
		}
	}
}
private boolean isClicked(Position p,Stats s){
	return Point2D.distance(p.xPos(), p.yPos(),s.getPos().xPos()+10 , s.getPos().yPos()+10)<10;
	
}
public void addRotations(double mag){
	for(Turtle t:turtList)
		if(t.matchFilter(turtFilter)){
			t.addRotation(mag);
		}
}
public Stats displayStats(){
	return getStats(turt);
}
	public Stats getStats(Turtle t){
		return t.getStats();
	}

	public String getImage(){
		return chosenImage;
	}

	public void setFilter(int filter){
		//System.out.println(filter+ " filter");
		turtFilter=filter;
	}
	public void rotateImage() {
for(Turtle t:turtList)
	if(t.matchFilter(turtFilter)){
		String imageString= "Turtle" + Math.random();
		Stats s = getStats(t);
		defineImageRotated(imageString,"-",0, "Turtle", s.getRot()%360);
		t.setImage(imageString);
	}
}
	public void suspendTurtles(){
		for(Turtle t:turtList)
			if(t.matchFilter(turtFilter))
				t.suspend();
	}
	public void setVelocities(double velocity) {
		for(Turtle t:turtList)
			if(t.matchFilter(turtFilter))
				t.setVelocity(velocity);
		
	}
}
