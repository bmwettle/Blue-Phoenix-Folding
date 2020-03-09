package origamiProject;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Oplanner extends JPanel   {
/**
	 * 
	 */
	paper myPaper;
int mouseX=0;
int mouseY=0;
Button picker;

	private static final long serialVersionUID = -1071442708667655401L;
public Oplanner(paper p) {
	super();
	myPaper=p;
	this.setDoubleBuffered(true);
	
	//mouseBox= new GRect(0,0,6,6);
	picker= new Button("pick");
this.add(picker);
	//this.setBackground(Color.blue);
}
void paintCommponent(Graphics g){
	super.paintComponent(g);
	//drawNodes(myPaper);
	//g.drawOval(mouseX-3, mouseY-3, 6, 6);
	
}

public void drawCursor(int x,int y) {
	//this.getGraphics().drawOval(x-3,y-3,6,6);
	mouseX=x;
	mouseY=y;
	picker.setLocation(mouseX, mouseY);
	this.setComponentZOrder(picker, 0);
	//System.out.println("at:"+x+","+y+"now");
}
public void drawNodes(paper p){
	Graphics g= this.getGraphics();
	int width= p.width;
	int height=p.height;
	int squareSize= Math.min(getWidth()/width,getHeight()/height);
	int smallSquareSize=squareSize/6;
	Iterator<node> it= p.getNodes();
		// plot the node, and lines conecting them.
	while(it.hasNext()) {
		node myNode= (node) it.next();
		
		g.drawOval(myNode.x*squareSize-myNode.size*squareSize, myNode.y*squareSize-myNode.size*squareSize, myNode.size*2*squareSize, myNode.size*2*squareSize);
		g.setColor(Color.blue);
		if( p.isSelcted(myNode)) {
			g.setColor(Color.ORANGE);
		}
		g.fillOval(myNode.x*squareSize-myNode.size*smallSquareSize, myNode.y*squareSize-myNode.size*smallSquareSize, myNode.size*2*smallSquareSize, myNode.size*2*smallSquareSize);
		g.setColor(Color.BLACK);
		ArrayList<node> connections= p.getConections(myNode);
		Iterator<node> conIt= connections.iterator();
		g.setColor(Color.GREEN);
		while(conIt.hasNext()) {
			node endNode= conIt.next();
			g.drawLine(myNode.getX()*squareSize, myNode.getY()*squareSize, endNode.getX()*squareSize, endNode.getY()*squareSize);
			
		}
		g.setColor(Color.RED);
		//System.out.println(p.getCuts(myNode));
		Iterator<node> cuts= p.getCuts(myNode).iterator();
		while(cuts.hasNext()) {
			node endNode= cuts.next();
			g.drawLine(myNode.getX()*squareSize, myNode.getY()*squareSize, endNode.getX()*squareSize, endNode.getY()*squareSize);
		}
		g.setColor(Color.BLACK);
	}
	
	for (int i=0;i<=height;i++){
		g.drawLine(0, i*squareSize, width*squareSize, i*squareSize);
	
	}
	for (int i=0;i<=width;i++){
		g.drawLine( i*squareSize,0,i*squareSize, height*squareSize);
	
	}
	picker.setLocation(mouseX, mouseY);
	this.setComponentZOrder(picker, 0);
}
}
