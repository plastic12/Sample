package mathsUtility;

public interface InnerProductSpace <S extends InnerProductSpace<S>>extends Group<S>
{
	public abstract double inner(S p1);
	public abstract S scaler(double scaler);
}
