package TurtleStuff;
import javax.swing.JComponent;

import frontEnd.Position;
import frontEnd.SlogoView;

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
	private TurtleManager manager; 
	
	//tempList
/*
List<turtles> get turtswithFilter()

 */
	public TurtleDrawer(TurtleManager manage){
		manager=manage;
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
		manager.addTurtle(turt);
		turt.setEngine(this);
	}
	public void doFrame(){
		moveObjects();
		checkClicked();
		SlogoView.updateInfo();


	}
	public void paintFrame(){
		for(Turtle t: manager.getTurtlesByID()){
			if(t.matchFilter(manager.getFilter()))
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
		for(Turtle t: manager.getTurtlesByID()){
		//make these call methods from the TurtleManager class
				if(checkKey('r')){
					t.setPen(JGColor.red);
				}
				if(checkKey('g')){
					t.setPen(JGColor.green);
				}
				if(checkKey('b')){
					t.setPen(JGColor.blue);
				}
				if(checkKey('o')){
					t.setPen(JGColor.orange);
				}
				if(checkKey('r')){
					t.raisePen();
				}
				if(checkKey('m')){
					t.lowerPen();
				}}
		clearLastKey();

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
		manager.addTurtle(turt);
		//System.out.println(turtList.size());
		turt.setEngine(this);
	}

private void checkClicked(){
	if(getMouseButton(1)){
		clearMouseButton(1);
		JGPoint p=getMousePos();
		manager.selectClicked(new Position(p.x,p.y));
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

}
