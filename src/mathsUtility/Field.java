package mathsUtility;

public interface Field <F extends Field<F>>extends Ring<F>
{
	public abstract F inverse();
	public abstract F one();
}
