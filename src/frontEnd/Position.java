package frontEnd;

public class Position {
	private double x;
	private double y;
public Position(double xPos, double yPos){
	x=xPos;
	y=yPos;
}
public double xPos(){
	return x;
}
public double yPos(){
	return y;
}
@Override
public String toString(){
	return xPos()+" "+yPos();
}
@Override 
public boolean equals(Object o){
	if(!(o instanceof Position)){
		return false;
	}
	Position p=(Position) o;
	boolean x=p.xPos()==xPos();
	boolean y=p.yPos()==yPos();
	return x&&y;
}
@Override
public int hashCode(){
	return (int) (xPos()*yPos());
}
}
