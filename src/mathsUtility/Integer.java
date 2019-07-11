package mathsUtility;

public final class Integer implements Ring<Integer>
{
	private int value;
	public Integer(){}
	public Integer(int v){value=v;}
	public Integer mult(Integer r2) {return new Integer(value*r2.value);}

	public Integer sum(Integer r2) {return new Integer(value+r2.value);}
	public Integer neg() {return new Integer(-value);}
	public int get(){return value;}
	public void print(){System.out.print(value);}
	public Integer zero() {return new Integer(0);}
	@Override
	public boolean equalTo(Integer r2) {return value==r2.value;}

}
