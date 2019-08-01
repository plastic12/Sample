package graphicsUtil;

public class Point 
{
	public double x;
	public double y;
	public Point(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	public Point sum(Vector r2) {
		return new Point(x+r2.getX(),y+r2.getY());
	}
	public Point AffineCom(Point r2,double valueR1)
	{
		return new Point(x*valueR1+(1-valueR1)*r2.x,y*valueR1+(1-valueR1)*r2.y);
	}
	public Vector distance(Point p1)
	{
		return new Vector(x-p1.x,y-p1.y);
	}
	public void print() {
		System.out.println("x: "+x+" y: "+y);
	}
}
