package graphicsApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgPro 
{
	public static BufferedImage rescale(BufferedImage img,double x_scale,double y_scale)
	{
		BufferedImage output=new BufferedImage((int)(img.getWidth()*x_scale),(int)(img.getHeight()*y_scale),img.getType());
		Graphics2D g2=output.createGraphics();
		g2.drawImage(img, 0, 0, output.getWidth(), output.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
		g2.finalize();
		return output;
	}
	public static void inverse(BufferedImage img)
	{
		for(int i=0;i<img.getWidth();i++)
		{
			for(int j=0;j<img.getHeight();j++)
			{
				int color=img.getRGB(i, j);
		    	int blue = color & 0xff;
		    	int green = (color & 0xff00) >> 8;
		    	int red = (color & 0xff0000) >> 16;
		    	blue=255-blue;
		    	green=255-green;
		    	red=255-red;
		    	img.setRGB(i, j, new Color(red,green,blue).getRGB());
			}
		}
	}

}
