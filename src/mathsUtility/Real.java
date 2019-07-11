package mathsUtility;

public final class Real implements Field<Real>
{
	private double value;

	public Real(){}
	public Real(double v){value=v;}
	public Real mult(Real r2) {return new Real(value*r2.value);}

	public Real sum(Real r2) {return new Real(value+r2.value);}

	public Real neg() {return new Real(-value);}
	public void print(){System.out.print(value);}
	public void setValue(double v){value=v;}
	public Real inverse() 
	{
		if(value==0)
			return null;
		else
			return new Real(1/value);
	}
	public double get(){return value;}
	@Override
	public Real zero() {return new Real();}
	@Override
	public Real one() {return new Real(1);}
	@Override
	public boolean equalTo(Real r2) 
	{return value==r2.value;}

}
