package graphicsUtil;

import mathsUtility.Group;
import mathsUtility.InnerProductHelper;

public class Vector extends InnerProductHelper<Vector>
{
	private double x;
	private double y;
	public Vector(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	public boolean equalTo(Vector r2) 
	{
		if(r2.x==x&&r2.y==y)
			return true;
		return false;
	}
	public Vector sum(Vector r2) {
		return new Vector(x+r2.x,y+r2.y);
	}
	public Vector neg() {
		return new Vector(-x,-y);
	}
	public Vector zero() {
		return new Vector(0,0);
	}
	/*
	*/
	public void print() {
		System.out.println("x: "+x+" y: "+y);
	}
	@Override
	public double inner(Vector p1) 
	{
		return x*p1.x+y*p1.y;
	}
	public double getX(){return x;}
	public double getY(){return y;}
	public static void main(String[] args)
	{
		Vector a=new Vector(0,0);
		Vector b=new Vector(1,0);
		Vector c=new Vector(0,1);
		System.out.println(a.norm());
		System.out.println(b.norm());
		System.out.println(c.norm());
		System.out.println(b.angle(c));
		System.out.println(c.angle(b));
		
	}
	public Vector rotate(double theta)
	{
		double x=this.x*Math.cos(theta)-this.y*Math.sin(theta);
		double y=this.x*Math.sin(theta)+this.y*Math.cos(theta);
		return new Vector(x,y);
	}
	@Override
	public Vector scaler(double scaler) {
		return new Vector(scaler*x,scaler*y);
	}
}
