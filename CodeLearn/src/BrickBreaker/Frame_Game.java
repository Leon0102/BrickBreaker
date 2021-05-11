package BrickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Frame_Game extends JPanel implements KeyListener, ActionListener {
	
		private boolean play = false;
		private int score =0;
		private int totalbrick =21;
		
		private Timer timer;
		private int delay =20;
		private int playerX=310;
		private int ballposX=120;
		private int ballposY=350;
		private int ballXir=-3;
		private int ballYir=-4;
		
		private MapGenerator map;
		public Frame_Game()
		{	
			addKeyListener(this);
			map = new MapGenerator(6,10);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			setSize(1000,600);
			
			timer = new Timer(delay,this);
			timer.start();
		}
		public void paint(Graphics g)
		{
			//background
			g.setColor(Color.black);
			g.fillRect(1, 1, 1000, 600);
			
			//map
			map.draw((Graphics2D) g);
			
			//score
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString(""+score, 950, 500);
			//borders
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, 10, 600);
			g.fillRect(0, 0, 1000, 35);
			g.fillRect(990, 0, 10, 600);
			
			//the paddle
			g.setColor(Color.blue);
			g.fillRect(playerX, 550, 200, 4);
			
			//the ball
			g.setColor(Color.ORANGE);
			g.fillOval(ballposX, ballposY, 20, 20);
			
			if(ballposY>550)
			{
				play=false;
				ballXir=0;
				ballYir=0;
				g.setColor(Color.red);
				g.setFont(new Font("serif",Font.BOLD,35));
				g.drawString("Game Over, Score: "+score, 330, 280);
				
				g.setFont(new Font("serif",Font.BOLD,30));
				g.drawString("Press Enter to Start" , 350, 310);
			}
			g.dispose();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timer.start();
			if(play)
			{
				if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,200,8)))
				{
					ballYir=-ballYir;
				}
				A:for(int  i=0;i<map.map.length;i++)
				{
					for(int j =0;j<map.map[0].length;j++)
					{
						if(map.map[i][j]>0)
						{
							int brickX = j*map.brickwidth+80;
							int brickY = i*map.brickheight+50;
							int brickwidth =map.brickwidth;
							int brickheight=map.brickheight;
							
							Rectangle rect = new Rectangle(brickX, brickY,brickwidth,brickheight);
							Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
							Rectangle brickrect = rect;
							
							if(ballrect.intersects(brickrect))
							{
								map.setBrickValue(0, i, j);
								totalbrick--;
								score+=5;
							if(ballposX +19 <= brickrect.x || ballposX +1 >= brickrect.x + brickrect.width)
							{
								ballXir= -ballXir;
							}else {
								ballYir=-ballYir;
							}
							break A;
							}
						}
					}
				}
				ballposX-=ballXir;
				ballposY-=ballYir;
				if(ballposX<10)
				{
					ballXir*=-1;
				}
				if(ballposY<40)
				{
					ballYir*=-1;
				}
				if(ballposX>960)
				{
					ballXir*=-1;
				}
				if(ballposY>560)
				{
					ballYir*=-1;
				}
			}
			repaint();
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
				if(playerX>=1000)
				{
					playerX=600;
				}
				else {
					moveRight();
				}
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				if(playerX>=1000)
				{
					playerX=600;
				}
				else {
					moveLeft();
				}
			}
			if(e.getKeyCode()== KeyEvent.VK_ENTER)
			{
				if(!play)
				{
					 play = true;
					 playerX=310;
					 ballposX=120;
					 ballposY=350;
					 ballXir=-6;
					 ballYir=-8;
					 score=0;
					 totalbrick=21;
					 map = new MapGenerator(6,10);
					 
					 repaint();
				}
			}
		}
		public void moveRight()
		{
			play=true;
			playerX+=20;
		}
		public void moveLeft()
		{
			play=true;
			playerX-=20;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Da bam phim: " + e.getKeyCode());
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}  
}
