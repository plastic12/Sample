package mathsUtility;

public class Matrix <R extends Ring<R>>
{
	protected int width;
	protected int height;
	protected R[][] m;
	public Matrix(int width,int height,R sample)
	{
		this.width=width;
		this.height=height;
		m=(R[][])new Ring[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				m[i][j]=sample.zero();
	}
	public Matrix(R[][] m)
	{
		this.m=m;
		width=m.length;
		height=m[0].length;
	}
	//operation
	public Matrix<R> multM(Matrix<R> m2) 
	{
		if(height!=m2.width)
		{
			System.out.println("the dimension doesn't match");
			return null;
		}
		else
		{
			R[][] output=(R[][])new Ring[width][m2.height];
			for(int i=0;i<width;i++)
				for(int j=0;j<m2.height;j++)
					output[i][j]=m[i][0].mult(m2.get(0, j));
			for(int i=0;i<width;i++)
				for(int j=0;j<m2.height;j++)
					for(int k=1;k<height;k++)
						output[i][j]=output[i][j].sum(m[i][k].mult(m2.get(k, j)));
			return new Matrix<R>(output);
		}
	}
	public Matrix<R> multE(Matrix<R> m2)
	{
		if(width!=m2.width||m2.height!=m2.height)
			return null;
		else
		{
			R[][] output=(R[][])new Ring[width][height];
			for(int i=0;i<width;i++)
				for(int j=0;j<height;j++)
					output[i][j]=m[i][j].mult(m2.get(i, j));
			return new Matrix<R>(output);
		}
	}
	public Matrix<R> multE(R r)
	{
		R[][] output=(R[][])new Ring[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				output[i][j]=m[i][j].mult(r);
		return new Matrix<R>(output);
	}

	public Matrix<R> multEAss(Matrix<R> m2)
	{
		if(width!=m2.width||m2.height!=m2.height)
			return this;
		else
		{
			for(int i=0;i<width;i++)
				for(int j=0;j<height;j++)
					m[i][j]=m[i][j].mult(m2.get(i, j));
			return this;
		}
	}
	public Matrix<R> multEAss(R r)
	{
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				m[i][j]=m[i][j].mult(r);
		return this;
	}

	public Matrix<R> sumM(Matrix<R> m2)
	{
		if(width!=m2.width||m2.height!=m2.height)
			return null;
		else
		{
			R[][] output=(R[][])new Ring[width][height];
			for(int i=0;i<width;i++)
				for(int j=0;j<height;j++)
					output[i][j]=m[i][j].sum(m2.get(i, j));
			return new Matrix<R>(output);
		}
	}
	public Matrix<R> sumM(R r)
	{
		R[][] output=(R[][])new Ring[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				output[i][j]=m[i][j].sum(r);
		return new Matrix<R>(output);
	}
	public Matrix<R> sumMAss(Matrix<R> m2)
	{
		if(width!=m2.width||m2.height!=m2.height)
			return this;
		else
		{
			for(int i=0;i<width;i++)
				for(int j=0;j<height;j++)
					m[i][j]=m[i][j].sum(m2.get(i, j));
			return this;
		}
	}
	public Matrix<R> sumMAss(R r)
	{
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				m[i][j]=m[i][j].sum(r);
		return this;
	}



	public Matrix<R> T()
	{
		R[][] new_m=(R[][])new Ring[height][width];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				new_m[j][i]=m[i][j];
		return new Matrix<R>(new_m);
	}
	public Matrix<R> customFunc(Func<R,R> f)
	{
		R[][] new_m=(R[][])new Ring[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				new_m[i][j]=f.func(m[i][j]);
		return new Matrix<R>(new_m);
	}
	public Matrix<R> customMutator(Func<R,R> f)
	{
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				m[i][j]=f.func(m[i][j]);
		return this;
	}
	//accessor
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public R get(int l1,int l2) {return m[l1][l2];}
	public void set(int l1,int l2,R value) {m[l1][l2]=value;}
	public void print()
	{
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				m[i][j].print();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public Matrix neg()
	{
		R[][] output=(R[][])new Ring[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				output[i][j]=m[i][j].neg();
		return new Matrix<R>(output);
	}
	public static void main(String[] args)
	{
		Real[][] test=new Real[2][3];
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++)
				test[i][j]=new Real();
		test[1][2].setValue(1);;
		test[0][2].setValue(1);;
		Matrix<Real> m=new Matrix<Real>(test);
		m.print();
		m.T().print();
		m.multM(m.T()).print();
		System.out.println("expotential");
		m.customFunc((a)->{return new Real(Math.exp(a.get()));}).print();;
	}
}
