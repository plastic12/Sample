package mathsUtility;

public abstract class InnerProductHelper <S extends InnerProductHelper<S>>implements InnerProductSpace<S>
{
	public double angle(InnerProductHelper<S> v2)
	{
		double a=Math.abs(inner((S) v2))/norm()/v2.norm();
		return Math.acos(a);
	}
	public double norm()
	{
		return Math.sqrt(this.inner((S) this));
	}
	public S unitVec()
	{
		if(norm()==0)
			return this.zero();
		else
			return this.scaler(1/norm());
	}
}
