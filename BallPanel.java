
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


import javax.swing.*;


public class BallPanel extends JPanel{

	//properties
	private Timer timer;
	private ArrayList<Ball> balls;
	private Collision collision;
	
	//constructor
	public BallPanel(double cor)
	{
		//initializing
		
		balls = new ArrayList<Ball>();
		collision = new Collision(balls,cor);
		collision.setSize(this);
		timer = new Timer(10, new TimerListener());
		
		//panel properties
		MyMouseListener mouseListener = new MyMouseListener();
		addMouseListener(mouseListener);
		
		setBackground(Color.YELLOW);
		this.setPreferredSize(new Dimension(300,300));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Ball i:balls)
			i.draw(g);
	}
	
	//methods
	public ArrayList<Ball> getBalls()
	{
		return balls;
	}
	
	public void addBall(Ball a)
	{
		balls.add(a);
	}
	
	public void pauseTimer()
	{
		timer.stop();
	}
	
	public void playTimer()
	{
		timer.start();
	}
	
	public void resetTimer(double cor)
	{
		balls = new ArrayList<Ball>();
		collision = new Collision(balls,cor);//i did this because, after resetting collision should collide according to the state of the elasticity slider.
		collision.setSize(this);
	}
	
	public void setBackground1(Color color)
	{
		this.setBackground(color);
	}
	
	public Collision getCollision()
	{
		return collision;
	}
	
	//by using this method, I am going to make every two ball one collision and save them in collision arrayList, so that when
	//every actionPerformed is called, instead of looking every ball and creating collisions, just checks created collisions by this method
	private class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			collision.update();
			repaint();
		}
	}

	private class MyMouseListener implements MouseListener
	{
		Ball ball;
		Point initial, end;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			initial = e.getPoint();
			ball = new Ball (30,new Vector (initial.x, initial.y), new Vector ());
			
			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			int deltaX, deltaY;
			
			end = e.getPoint();
			
			deltaX = initial.x - end.x;
			deltaY = initial.y- end.y;
			ball.setSpeed(deltaX / 8, deltaY /8);//if I directly set deltaX as a speedX then it is very fast for balls' velocity
			addBall(ball);
			repaint();
		}
	
		
	}
}
