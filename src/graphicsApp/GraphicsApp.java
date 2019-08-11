package graphicsApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;
import org.xml.sax.SAXException;

import graphicsUtil.Point;
import graphicsUtil.Vector;


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
	public static void writeDOM(Document doc,OutputStream os) throws UnsupportedEncodingException, FileNotFoundException, TransformerException
	{
        DOMSource source = new DOMSource(doc);
        Writer writer =new OutputStreamWriter(os,"UTF-8");
        StreamResult result = new StreamResult(writer);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
	}
	public static void SVGDocToPNG(SVGDocument doc,OutputStream os) throws FileNotFoundException, TranscoderException
	{
        TranscoderInput input=new TranscoderInput(doc);
        TranscoderOutput output_png_image = new TranscoderOutput(os);
        PNGTranscoder my_converter = new PNGTranscoder();
        my_converter.transcode(input, output_png_image);
	}
	public static BufferedImage streamToBitmap(ByteArrayOutputStream os)
	{
		byte[] data = os.toByteArray();
		ByteArrayInputStream input = new ByteArrayInputStream(data);
		try {
			BufferedImage image = ImageIO.read(input);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	//make a svggraphics with viewbox width and height
	public static SVGDocument makeSVGDocument()
	{
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		SVGDocument doc = (SVGDocument)impl.createDocument(svgNS, "svg", null);
        return doc;
	}
	public static SVGGraphics2D createGraphics(SVGDocument doc)
	{
		return new SVGGraphics2D(doc);
	}
	public static void dispose(SVGGraphics2D g, SVGDocument doc)
	{
		g.getRoot(doc.getDocumentElement());
		g.dispose();
		return;
	}
	public static void writeImg(BufferedImage image ,OutputStream os) throws IOException
	{
		ImageIO.write(image, "png", os);
	}
	public static Point[] sampleArc(Point end,Point start,double distance,int nPoints)
	{
		if(nPoints<=0)
		{
			System.out.println("the point require is at least one");
			return null;
		}
		Point[] output=new Point[nPoints];
		if(nPoints==1)
		{
			output[0]=start;
			return output;
		}
		if(nPoints==2)
		{
			output[0]=start;
			output[1]=end;
			return output;
		}
		Point center=end.AffineCom(start, 0.5).sum(end.distance(start).unitVec().rotate(Math.PI/2).scaler(distance));
		double angle=end.distance(center).angle(start.distance(center));
		Vector r=start.distance(center);
		for(int i=0;i<nPoints;i++)
		{
			output[i]=center.sum(r.rotate(angle*i/(nPoints-1)));
		}
		return output;
	}
	public static void printNode(Node node)
	{
		System.out.println(node.getNodeName());
    	NodeList nodes=node.getChildNodes();
    	for(int i=0;i<nodes.getLength();i++)
    	{
    		Node n=nodes.item(i);
    		printNode(n);
    	}
		System.out.println(node.getNodeName()+"/");
	}
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException, TranscoderException 
    {
        SVGDocument document =makeSVGDocument();
        SVGGraphics2D g=createGraphics(document);
        g.setColor(new Color(237,28,36));
        g.fillOval(32, 32, 448, 448);
    	g.setColor(new Color(252,134,3));
    	g.fillOval(96,96, 320, 320);
    	g.setColor(new Color(255,242,0));
    	g.fillOval(160, 160, 192, 192);
    	dispose(g,document);
    	rescaleSVG(512,512,document);
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	SVGDocToPNG(document,output);
    	BufferedImage image=streamToBitmap(output);
    	writeImg(image,new FileOutputStream(new File("test1.png")));
    	SVGDocToPNG(document,new FileOutputStream(new File("test2.png")));
    	//writeDOM(document,new FileOutputStream(new File("test.svg")));
    }

}
