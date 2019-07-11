package mathsUtility;

public interface Group <G extends Group<G>>
{
	public abstract boolean equalTo(G r2);
	public abstract G sum(G r2);//r+r2
	public abstract G neg();
	public abstract G zero();
	public abstract void print();

}
