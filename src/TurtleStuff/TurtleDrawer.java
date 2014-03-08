package TurtleStuff;
import javax.swing.JComponent;

import frontEnd.Position;
import frontEnd.SlogoView;

import DrawingPanel.VariableDrawingPanel;
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
@SuppressWarnings("serial")
public class TurtleDrawer extends JGEngine {
	private static final String DEFAULT_IMAGE = "turtle.gif";
	private static final String TILE_ID = "Turt";
	private static final String TURTLE_ID = "Turtle";
	private Turtle turt;
	private String chosenImage;
	private TurtleManager manager; 
	//tempList
/*
List<turtles> get turtswithFilter()

 */
	public TurtleDrawer(TurtleManager manage){
		dbgShowBoundingBox(true);
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
		defineImage(TURTLE_ID,TILE_ID,0,DEFAULT_IMAGE,"-");
		turt=new Turtle(0);
		manager.addTurtle(turt);
		turt.setEngine(this);
	}
	public void doFrame(){
		moveObjects();
		checkClicked();
		if(SlogoView.viewStats()!=null)
		SlogoView.viewStats().updateInfo();
		VariableDrawingPanel.fillVariables();


	}
	public void paintFrame(){
		for(Turtle t: manager.getFilteredTurtles()){
				t.runPen(2,true);
				
		}
		manager.highlightSelectedTurtles();
		manager.highlightTurtle();
	}

	public void addnewTurtle(int id){
		
		defineImage(TURTLE_ID,TILE_ID,0,DEFAULT_IMAGE,"-");
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
	return manager.displayStats();
}
	public Stats getStats(Turtle t){
		return t.getStats();
	}

	public String getImage(){
		return chosenImage;
	}
	
	public void clearLines(){
		turt.clearLines();
	}
	
	public void clear(){
		removeObjects(TURTLE_ID, 0);
		List<Turtle> turtlist = manager.getAllTurtles();
		for(Turtle t: turtlist){
			t.clearLines();
		}
		manager.clearTurtList();
	}
	
public void reAddTurtles(List<Turtle> turtlist){
		int i=0;
		System.out.print(turtlist.size());
		for(Turtle t: turtlist){
			Stats currStats = t.getStats();
			defineImage(TURTLE_ID,TILE_ID,0,DEFAULT_IMAGE,"-");
			turt=new Turtle(i);
			manager.addTurtle(t);
			turt.setEngine(this);
			turt.setTarget(currStats.getPos());
			turt.setRotation(currStats.getRot());
			i++;
		}
	}

	public List<Turtle> getAllTurtles(){
		return manager.getAllTurtles();

	}
	
	


}
