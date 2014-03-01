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
}
