package tool;

import java.util.Date;

public class LoopSample 
{
	
	public static void main(String[] args)
	{
		SpeedTest sT=new SpeedTest();
		while(true)
		{
			sT.start();
			System.out.println(new Date());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sT.end();
				try {
					Thread.sleep(1000-sT.showTime()/1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}
