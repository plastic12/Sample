package graphicsApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.xml.sax.SAXException;


public class GraphicsApp 
{
	public static BufferedImage readImg(String filename) throws IOException
	{
		return ImageIO.read(new File(filename));
	}
	public static SVGDocument readSVG(String filename) throws IOException
	{
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        //String uri = "http://www.example.org/diagram.svg";
        String uri = new File(filename).toURI().toString();
        return (SVGDocument)f.createDocument(uri);
	}
	public static void rescaleSVG(int width,int height,SVGDocument doc)
	{
        Element svgElement=doc.getDocumentElement();
        svgElement.setAttributeNS(null, "width", ""+width+""); 
        svgElement.setAttributeNS(null, "height", ""+height+"");
	}
	public static void writeDOM(Document doc,String filename) throws UnsupportedEncodingException, FileNotFoundException, TransformerException
	{
        DOMSource source = new DOMSource(doc);
        Writer writer =new OutputStreamWriter(new FileOutputStream(new File(filename)),"UTF-8");
        StreamResult result = new StreamResult(writer);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
	}
	public static void SVGDocToPNG(SVGDocument doc,String filename) throws FileNotFoundException, TranscoderException
	{
        TranscoderInput input=new TranscoderInput(doc);
        OutputStream png_ostream = new FileOutputStream(filename);
        TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
        PNGTranscoder my_converter = new PNGTranscoder();
        my_converter.transcode(input, output_png_image);
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
	//make a svggraphics with viewbox width and height
	public static SVGGraphics2D makeSVGGraphics()
	{
        DOMImplementation domImpl =GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGraphics2D g=new SVGGraphics2D(document);
        return g;
	}
	public static void writeSVGgraphics(Writer out,SVGGraphics2D g,int width,int height) throws SVGGraphics2DIOException
	{
    	Element root=g.getRoot();
        root.setAttributeNS(null, "viewBox", "0 0 "+width+" "+height);
        g.stream(root,out,true,false);
	}
	public static void writeImg(BufferedImage image ,String filename) throws IOException
	{
		ImageIO.write(image, "png", new File(filename));
	}
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException, TranscoderException 
    {
    	
    	SVGGraphics2D g=makeSVGGraphics();
    	g.setColor(new Color(255,174,201));
    	g.fillRect(0, 0, 512, 512);
    	g.setColor(new Color(128,255,0));
    	g.fillRect(192, 32, 128, 448);
    	g.fillRect(32, 192, 448, 128);
    	writeSVGgraphics(new OutputStreamWriter(new FileOutputStream(new File("test.svg")),"UTF-8"),g,512,512);
        
    	/*
    	SVGDocument doc=readSVG("test.svg");
    	rescaleSVG(32,32,doc);
    	SVGDocToPNG(doc,"test.png");
    	*/
    	/*
        DOMImplementation domImpl =GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setPaint(Color.red);
        svgGenerator.fillRect(10, 10, 100, 100);
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(new FileOutputStream(new File("test.svg")), "UTF-8");
        svgGenerator.stream(out, useCSS);
        */
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
