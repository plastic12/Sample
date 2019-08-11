package tool;

public class SpeedTest 
{
	private long start;
	private long end;
	public SpeedTest(){
		reset();
	}
	public void start()
	{
		start=System.nanoTime();
	}
	public void end()
	{
		end=System.nanoTime();
	}
	public void reset()
	{
		start=0;
		end=0;
	}
	public long showTime()
	{
		return end-start;
	}
}
