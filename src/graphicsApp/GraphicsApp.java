package graphicsApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class GraphicsApp 
{
	public static BufferedImage readImg(String filename) throws IOException
	{
		return ImageIO.read(new File(filename));
	}
	public static BufferedImage makeSquare(int width,Color c)
	{
		BufferedImage image=new BufferedImage(width,width,BufferedImage.TYPE_INT_RGB);
		Graphics2D g=image.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.finalize();
		return image;
	}
	public static void writeImg(BufferedImage image ,String filename) throws IOException
	{
		ImageIO.write(image, "png", new File(filename));
	}
    public static void main(String[] args) throws IOException 
    {
    	BufferedImage image=makeSquare(32,Color.blue);
    	ImgPro.inverse(image);
    	writeImg(image,"yellow.png");
    }
    	/*
    	String input;
    	BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Input number of image to combine");
    	int size=Integer.parseInt(stdin.readLine());
    	BufferedImage[] image=new BufferedImage[size];
    	for(int i=0;i<size;i++)
    	{
    		System.out.println("Input the path of image");
    		input=stdin.readLine();
    		image[i] = ImageIO.read(new File(input));
    		System.out.println("Image has been loaded");
    	}
    	int row,column;
    	do{
    		System.out.println("Input the row of the combined item");
    		row=Integer.parseInt(stdin.readLine());
    		System.out.println("Input the column of the combined item");
    		column=Integer.parseInt(stdin.readLine());
    		if(row*column==size)
    			break;
    		System.out.println("The row,column is not matched to the size");
    	}while(true);
        int chunkWidth = image[0].getWidth();
        int chunkHeight = image[0].getHeight();
        for(int i=0;i<size;i++)
        {
        	if(image[i].getWidth()!=chunkWidth)
        	{
        		System.out.println("Make sure images all have same width");
        		return;
        	}
        	if(image[i].getHeight()!=chunkHeight)
        	{
        		System.out.println("Make sure images all have same height");
        		return;
        	}
        }
        BufferedImage output=new BufferedImage(chunkWidth*column,chunkHeight*row,image[0].getType());
        Graphics2D gr =output.createGraphics();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                //Initialize the image array with image chunks

                // draws the image chunk
                gr.drawImage(image[x*column+y], chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth,  chunkHeight * x + chunkHeight,0 ,0 ,chunkWidth ,chunkHeight, null);
            }
        }
        gr.dispose();
        System.out.println("Combining done");

        //writing mini images into image files
        ImageIO.write(output, "png", new File("output/output.png"));
    }
    */

}
