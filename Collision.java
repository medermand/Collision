

import java.util.ArrayList;

import javax.swing.JPanel;




public class Collision {
	//properties
	
	private ArrayList<Ball> balls;
	JPanel size; //To get size of BallPanel
	double COR; // Coefficient of Restitution
	//constructor
	public Collision (ArrayList<Ball> balls, double cor)
	{
		this.balls = balls;
		COR = cor;
	}
	
	public void update(){
		for(int i=0; i < balls.size(); i++){
			collisionWithWall(balls.get(i));
			for(int j=i+1; j < balls.size(); j++)
				collisionOfTwoBall(balls.get(i), balls.get(j));
		}
		
		for(int i=0; i < balls.size(); i++)
			balls.get(i).updateLocation();
	}
	//methods 
	//mutators
	public void setCOR (double cor)
	{
		this.COR = cor;
	}
	
	public void setSize(JPanel size){
		this.size = size;
	}
	
	public void collisionOfTwoBall(Ball ball1, Ball ball2)
	{
		if(doesTouch(ball1, ball2))
		{
			
			
//			System.out.println(COR);
			//http://williamecraver.wix.com/elastic-equations formulas taken from
				double vx1, vy1,vx2, vy2;//these velocities are after the collision
				double v1,v2;//these are the velocities before the collision, these are magnitudes
				double vxr1, vxr2;//these are just notations, however they mean vx rotated
				double theta1, theta2;
				double phi;
				double m1,m2;//masses of the balls
				m1 = ball1.getMass();
				m2 = ball2.getMass();
				theta1 = ball1.getVelocity().getTeta();
				theta2 = ball2.getVelocity().getTeta();
				v1 = ball1.getSpeed();
				v2 = ball2.getSpeed();
				phi = angleWhenBallsTouch(ball1, ball2);//this is an angle when the two balls touch. angle between the line which connects two ball and x axis

				vxr1 = (m2 * v2 * Math.cos(theta2- phi) * (1 + COR) + (m1 - m2 * COR) * v1 * Math.cos(theta1 - phi))/(m1 + m2);  
				vxr2 = (m1 * v1 * Math.cos(theta1 - phi) * (1 + COR) + (m2 - COR * m1) * v2 * Math.cos(theta2- phi))/(m1 + m2);
				
				vx1 = vxr1 * Math.cos(phi) + v1 * Math.sin(theta1 - phi) * Math.cos(phi + Math.PI/2);
				vy1 = vxr1 * Math.sin(phi) + v1 * Math.sin(theta1 - phi) * Math.sin(phi + Math.PI/2);
				vx2 = vxr2 * Math.cos(phi) + v2 * Math.sin(theta2 - phi) * Math.cos(phi + Math.PI/2);
				vy2 = vxr2 * Math.sin(phi) + v2 * Math.sin(theta2 - phi) * Math.sin(phi + Math.PI/2);
				ball1.setSpeed(vx1, vy1);
				ball2.setSpeed(vx2, vy2);

				
			}
		}

	
	
	//ballPanelden for loop jugurtkondo, al jakta collisions degen arrayList arkyluu tekweret, owonduktan eki balldyn birdei tekweriluusu
	//uchun collision dep turup ele collisionWithBall degen methodtu chakyram ekoo ten update bolot,
	
	public void collisionWithWall(Ball ball)
	{
		double a = ball.getLocationX();
		double b = ball.getLocationY();
		double x = ball.getLocationX();
		double y = ball.getLocationY();
		double r = ball.getBallRadius();
		
		if (x <= r || x >= size.getSize().width -r)
		{
			ball.setSpeedX(- COR * ball.getSpeedX());
		}
		if (y <= r || y >= size.getSize().height - r)
			ball.setSpeedY(-COR * ball.getSpeedY());
		
		if (a <+ r)
			ball.setLocation(r + 1, y);
		else if (a >= size.getSize().width -r)
			ball.setLocation(size.getSize().width -r - 1, y);
		else if (b <= r)
			ball.setLocation(x, r + 1);
		else if (b >= size.getSize().height - r)
			ball.setLocation(x, size.getSize().height - r - 1 );
	}
	
	public boolean doesTouch(Ball ball1, Ball ball2)
	{
		Vector v1 = ball1.getLocation();
		Vector v2 = ball2.getLocation();
		double r1 = ball1.getBallRadius();
		double r2 = ball2.getBallRadius();
		double distance = Vector.distance(v1, v2);
		if (distance <= r1 + r2)
		{
			double relX = v1.getX() - v2.getX();
			double relY = v1.getY() - v2.getY();
			// Take the arctan to find the collision angle.
			double collisionAngle = Math.atan2(relY, relX);
			
			double minDist = ball1.getBallRadius() + ball2.getBallRadius();
			double overlap = minDist - distance;
			double toMove = overlap / 2;
			double newX = v1.getX() + (toMove * Math.cos(collisionAngle));
			double newY = v1.getY() + (toMove * Math.sin(collisionAngle));
			ball1.setLocation(newX, newY);
			newX = v2.getX() - (toMove * Math.cos(collisionAngle));
			newY = v2.getY() - (toMove * Math.sin(collisionAngle));
			ball2.setLocation(newX, newY);
		
			return true;}
		else
			return false;
	}
	
	//for test
	
	public double angleWhenBallsTouch(Ball ball1, Ball ball2)
	{
		if (doesTouch(ball1, ball2))
		{
			Vector v;
			double d1 = ball1.getLocation().getR();
			double d2 = ball2.getLocation().getR();
			Vector loc1 = ball1.getLocation();
			Vector loc2 = ball2.getLocation();
			
			if(d1 > d2)
				v = Vector.vectorSubtract(loc1, loc2);
			else
				v = Vector.vectorSubtract(loc2, loc1);
			return v.getTeta();
		}
		
		else
			return -1;
	}
	
	
}
