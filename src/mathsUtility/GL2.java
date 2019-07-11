package mathsUtility;

public class GL2<F extends 
Field<F>> extends Matrix<F> implements Group<GL2<F>>
{
	public GL2(F sample) {super(2,2,sample);}

	public GL2(F[][] m) throws Exception 
	{
		super(m);
		if(m.length!=2||m[0].length!=2)
			throw new Exception("the size should be 2 by 2");
		if(det().equalTo(m[0][0].zero()))
			throw new Exception("determinant should not be zero");
	}

	@Override
	public GL2<F> sum(GL2<F> r2) 
	{
			F[][] z=(F[][])new Field[width][r2.height];
			for(int i=0;i<width;i++)
				for(int j=0;j<r2.height;j++)
					z[i][j]=m[i][0].mult(r2.get(0, j));
			for(int i=0;i<width;i++)
				for(int j=0;j<r2.height;j++)
					for(int k=1;k<height;k++)
						z[i][j]=z[i][j].sum(m[i][k].mult(r2.get(k, j)));
			GL2<F> output=null;
			try {
				output= new GL2<F>(z);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
	}
	public F det()
	{
		return m[0][0].mult(m[1][1]).sum((m[0][1].mult(m[1][0])).neg());
	}
	@Override
	public GL2<F> neg() {
		F[][] z=(F[][])new Field[2][2];
		F f=det();
		z[0][0]=m[1][1].mult(f.inverse());
		z[0][1]=m[0][1].neg().mult(f.inverse());
		z[1][0]=m[1][0].neg().mult(f.inverse());
		z[1][1]=m[0][0].mult(f.inverse());
		GL2<F> output = null;
		try {
			output= new GL2<F>(z);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	@Override
	public GL2<F> zero() {
		F[][] z=(F[][])new Field[2][2];
		z[0][0]=m[0][0].one();
		z[0][1]=m[0][0].zero();
		z[1][0]=m[0][0].zero();
		z[1][1]=m[0][0].one();
		GL2<F> output = null;
		try {
			output= new GL2<F>(z);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	public static void main(String[] args)
	{
		Real[][] test=new Real[2][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				test[i][j]=new Real();
		test[0][0].setValue(2);
		test[1][0].setValue(1);
		test[1][1].setValue(2);
		try {
			GL2<Real> m=new GL2<Real>(test);
			System.out.println("test matrix:");
			m.print();
			System.out.println("inverse");
			m.neg().print();
			System.out.println("self mult");
			m.sum(m).print();
			System.out.println("zero");
			m.zero().print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean equalTo(GL2<F> r2) {
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				if(!m[i][j].equalTo(r2.m[i][j]))
					return false;
		return true;}
	
}
