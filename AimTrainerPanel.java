//change to be your packagename
package aimTrainer;

import java.awt.Color;
//all imports are necessary
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import java.util.Random;

//must 'extend' JPanel 
public class AimTrainerPanel extends JPanel
{
	//variables for the overall width and height
	private int w, h;
	
	//variable for target
	//outer red circle
	private int orx, ory, ors;
	//outer white circle
	private int owx, owy, ows;
	//inner red circle
	private int irx, iry, irs;
	//inner white circle
	private int iwx, iwy, iws;
	//space between layers
	private int l;
	
	//Random generator 
	private Random generator = new Random();
	
	//pointer variables
	private int px, py, ps;
	
	//score
	private int score;
	
	//sets up the initial panel for drawing with proper size
	public AimTrainerPanel(int w, int h)
	{
		this.w = w;
		this.h = h;
		this.setPreferredSize(new Dimension(w,h));
		
		this.setBackground(Color.GRAY);
		
		//initialize target variables
		l=24;
		//target layers sizes
		iws=l;
		irs=iws+l;
		ows=irs+l;
		ors=ows+l;
		//target layers coordinates
		//x
		orx=w/2-ors/2;
		owx=orx+l/2;
		irx=owx+l/2;
		iwx=irx+l/2;
		//y
		ory=h/2-ors/2;
		owy=ory+l/2;
		iry=owy+l/2;
		iwy=iry+l/2;
		
		this.addMouseListener(new Clicking());
		
		score=0;
		
		this.addMouseMotionListener(new PlayerMover());
		
		ps=16;
		px=w/4;
		py=h/4;
	}
	
	
	//all graphical components go here
	//this.setBackground(Color c) for example will change background color
	public void paintComponent(Graphics g)
	{
		//this line sets up the graphics - always needed
		super.paintComponent(g);

		//all drawings below here:
		
		//Score
		g.setColor(Color.BLACK);
		g.drawString("Score: "+score, 5, 15);
		
		//Target
		//Outter red circle
		g.setColor(Color.RED);
		g.fillOval(orx, ory, ors, ors);
		//Outter white circle
		g.setColor(Color.WHITE);
		g.fillOval(owx, owy, ows, ows);
		//Inner red circle
		g.setColor(Color.RED);
		g.fillOval(irx, iry, irs, irs);
		//Inner white circle
		g.setColor(Color.WHITE);
		g.fillOval(iwx, iwy, iws, iws);

		//pointer
		g.setColor(Color.BLACK);
		g.drawLine(px, py-ps, px, py+ps);
		g.drawLine(px-ps, py, px+ps, py);
	}
	
	private class PlayerMover implements MouseMotionListener 
	{
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			px=e.getX();
			py=e.getY();
			repaint();
		}	
	}
	
	private class Clicking implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			if(px>orx&&px<orx+ors&&py>ory&&py<ory+ors) {
				orx=generator.nextInt(w-ors);
				owx=orx+l/2;
				irx=owx+l/2;
				iwx=irx+l/2;
				ory=generator.nextInt(h-ors);
				owy=ory+l/2;
				iry=owy+l/2;
				iwy=iry+l/2;
				
				score++;
				
				repaint();
			}
		}
		public void mouseReleased(MouseEvent e) {}
	}
	
}
