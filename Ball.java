
import java.awt.*;
import java.util.*;



public class Ball {
	
	//properties
	private double mass;
	private Vector location;
	
	ArrayList<Point> position;//this is for getting every point of the ball as it changes its position to draw its track
	public final  double defaultMass = 30;//needs to be changed
	private double radiusOfBall;
	private Vector velocity;
	private Color ballColor;
	private boolean ballTrack;
	private boolean velDirection;
	private Color[] colors = {Color.MAGENTA,Color.BLUE,Color.GREEN,Color.GRAY,Color.BLACK,Color.ORANGE,Color.RED,Color.PINK};
	//constructors
	
	//this is default constructor
	public Ball()
	{
		
		mass = defaultMass;
		radiusOfBall = mass;
		location = new Vector(0,0);
		velocity = new Vector(0,0);
		position = new ArrayList<Point>();
		position.add(new Point(0,0));
		ballTrack = false;
	}
	
	public Ball(double mass, Vector location, Vector velocity)
	{
		Random r = new Random();
		int a=r.nextInt(8);
		this.mass = mass;
		radiusOfBall =  (2000/1999)*mass;
		this.location = location;
		this.velocity = velocity;
		position = new ArrayList<Point>();
		
		ballColor = colors[a];
		ballTrack = false;
		velDirection = false;
	}
	
	public void updateLocation(){
		setPosition(new Point((int)location.x, (int)location.y));
		setLocation(location.x + getSpeedX(), location.y + getSpeedY());
	}
	
	//METHOD
	
	//MUTATORs

	
	public void setLocation(double x, double y)
	{
		location.setCartesian(x, y);
//		System.out.println ("setLocation working ");
	}
	public void setPosition(Point p)
	{
		position.add(p);
	}
	
	public void setSpeedX( double x)
	{
		velocity.setCartesian(x, getSpeedY());
	}
	
	public void setSpeedY( double y)
	{
//		System.out.println ("SetSpeedY");
		velocity.setCartesian(getSpeedX(), y);
	}
	
	public void setSpeed(double x, double y)
	{
//		System.out.println ("SetSpeed");
		velocity.setCartesian(x, y);
//		System.out.println (getSpeedX()+ " " + getSpeedY());
	}
	
	public void setBallColor(Color c)
	{
		ballColor = c;
	}
	
	public void setBallTrack(boolean check)
	{
		ballTrack = check;
	}
	
	public void setVelDirection(boolean check)
	{
		velDirection = check;
	}
	
	//ACCESSORS
	public boolean getBallTrack()
	{
		return ballTrack;
	}
	
	public boolean getVelDirection()
	{
		return velDirection;
	}
	
	public Vector getLocation()
	{
		return location;
	}
	public double getLocationX()
	{
		return location.getX();
	}
	
	public double getLocationY()
	{
		return location.getY();
	}
	
	public double getBallRadius()
	{
		return radiusOfBall;
	}
	//this method is to return X or Y components of the velocity
	public Vector getVelocity()
	{
		return velocity;
	}
	
	public double getSpeed()
	{
		return velocity.getR();
	}
	
	
	
	public double getSpeedX()
	{
		return velocity.getX();
	}
	public double getSpeedY()
	{
		return velocity.getY();
	}
	
	public double getMass()
	{
		return mass;
	}
	
	public void draw(Graphics g)
	{
		int r = (int)radiusOfBall;
		double k=r/20;
		g.setColor(ballColor);
		g.fillOval((int)location.getX() - r, (int)location.getY() - r, 2*r, 2*r);
		
		int a =ballColor.getRGB();
        
        double r2=radiusOfBall;
        double locX=location.getX();
        double locY=location.getY();
        for(int n=0; n<21;n++){
        r2=r2-k;
        locX=locX+k/2;
        locY=locY+k/2;
        if(ballColor==Color.MAGENTA)
        	a=a-10;
        if(ballColor==Color.RED)
        	a=a+11;
        if(ballColor==Color.GREEN)
        	a=a+10;
        if(ballColor==Color.BLACK)
        	a=a+9;
        if(ballColor==Color.GRAY)
        	a=a+4;
        if(ballColor==Color.YELLOW)
        	a=a+10;
        if(ballColor==Color.PINK)
        	a=a+3;
        if(ballColor==Color.ORANGE)
        	a=a+9;
        if(ballColor==Color.BLUE)
        	a=a-7;
        if(ballColor==Color.CYAN)
        	a=a-5;
        Color newColor=new Color(a);
        g.setColor(newColor);
        g.fillOval((int) (locX - r2), (int)( locY - r2),(int) (2 * r2),(int)( 2 * r2));
        }
        
        if(velDirection)
        {
        
        	Vector vel = getVelocity();
            Ruler.drawArrow(getLocation(),Vector.product(5, vel) , g, Color.BLACK);
        }
        
        if (ballTrack)
        {
        	int length = position.size();
    		int[] X = new int[length];
    		int[] Y = new int[length];
    		for (int i = 0; i < length; i++)
    		{
    			X[i] = position.get(i).x;
    			Y[i] = position.get(i).y;
    		}
    		g.drawPolyline(X, Y, length);
        }
		
	}	
		
}

