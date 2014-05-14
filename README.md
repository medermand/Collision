Ball Class

•	properties: double mass, Vector location , ArrayList<Point>  position, Vector velocity, double radiusOfBall, Color ballColor;
•	constructors: *Ball(double mass, Vector location, Vector velocity){}
•	methods: draw(), updateLocation() and  Accessor-Mutator methodsfor all properties

This class has all properties of the ball, velocity, mass, color and location. The position property is used in order to save all the points when the ball changes its position, because we need it in order to draw the track of the ball. 
	There is one constructor, which takes mass, location, and velocity as a parameter, sets the color of the ball randomly, and sets the radius of the ball according to the given mass.
A draw() method is to draw the ball to an appropriate location and also the track of the ball using points in position ArrayList. This method also fills the balls color similar to 3D shape. 
An updateLocation() method updates the ball location according to its speed.

Collision Class

•	Properties: ArrayList<Ball> balls, JPanel size, double COR(Coefficient of Restitution)
•	Constructors:  Collision(ArrayList<Ball> balls)
•	Methods: void update(), boolean doesTouch(Ball ball1, Ball ball2), void collision-WithWall(Ball ball), void collisionOfTwoBall(Ball ball1, Ball ball2), void set-Size(),double angleWhenBallsTouch(Ball ball1, Ball ball2)

An update() method force to work all other methods as well, which the most crucial method in this class. When this method is called, the method calls collisionWithWall(Ball ball) and col-lisionOfTwoBall(Ball ball1, Ball ball2) methods.
 A doesTouch()  method returns true if two balls touch with each other or overlap. If balls overlap with each other, then the method sets their position to their borders so that they will never overlap during the collision. If they are far apart from each other then the method re-turns false.
If doesTouch() method return true, then balls collide with each other according to the laws of Physics in collisionOfTwoBall(Ball ball1, Ball ball2) method. An angleWhen-BallsTouch(Ball ball1, Ball ball2)  is used when balls collide.
In this class we need size of the JPanel where balls appear, because in collisionWithWall(Ball ball) method, ball needs to change their direction at the borders of the panel.
The COR is Coefficient of Restitution. If COR = 1, then the collision is totally inelastic, if COR = 0, collision is totally inelastic. Between 1 and 0, collision is partially elastic and inelas-tic. 

BallPanel Class

•	properties: ArrayList<Ball> balls, Timer timer, Collision collision
•	constructor: BallPanel(){}
•	method: paintComponent(), addBall (Ball ball), pauseTimer(), playTimer, ResetTimer()
•	subclass: TimerListener, MyMouseListener()


  In this class there are one or more than one balls and timer as a property. In construc-tor, these balls and timer initializes also specific properties of the BallPanel. A subclass TimerListener  is a class which implements ActionListener. This method is called by the timer, and this calls update method in Collision class. A paintComponent() paints every-time when timerListener called as an updated location, this method calls balls’ draw() method to draw balls itself and their track. By MyMouseListener() method, user able to create a balls using mouse on the panel. Mouse pressed position is the location of the ball, and the difference between the location mouse pressed and released is the magnitude of the ball’ speed. The methods related with timer, controls timer: one plays the timer, the other pauses.

FunctionPanel Class

	This class totally design and user interaction class. Thanks to this class, the program gets data from user and works appropriately. The class has various panels, textFields and buttons, which make understandable to users.
