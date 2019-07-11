package mathsUtility;

public interface Ring <R extends Ring<R>> extends Group<R>
{
	public abstract R mult(R r2);//r*r2
}
