

import javax.swing.*;
import java.awt.*;

public class Ruler {
	
	//CONSTANTS
	private static final int SPACE = 22, INTERVAL = 50, LENGTH_OF_LINES = 4;

	//Draw Coordinate System
	public static void drawRuler(JPanel panel, Graphics page){
		page.setColor(Color.BLACK);
		page.drawLine(SPACE, SPACE, panel.getSize().width, SPACE);
		page.drawLine(SPACE, SPACE, SPACE, panel.getSize().height);
		
		for(int i=INTERVAL; i < panel.getSize().width; i+=INTERVAL){
			page.drawLine(i, SPACE - LENGTH_OF_LINES/2, i, SPACE + LENGTH_OF_LINES/2);
			page.drawString(i+"", i-8, SPACE - 4);
		}
		for(int i=INTERVAL; i < panel.getSize().height; i+=INTERVAL){
			page.drawLine(SPACE - LENGTH_OF_LINES/2, i, SPACE + LENGTH_OF_LINES/2, i);
			page.drawString(i+"", 0, i+5);
		}
	}
	
	//Draw Arrows of Velocity or Acceleration
	public static void drawArrow(Vector startOfArrow, Vector velocityOfArrow, Graphics page, Color color){
		Vector endOfArrow = new Vector();
		Vector vectorForEndOfWings = new Vector();
		double lengthOfWings;
		double angleOfBodyOfArrow;
		
		endOfArrow.setCartesian(startOfArrow.getX() + velocityOfArrow.getX(), startOfArrow.getY() + velocityOfArrow.getY());
		lengthOfWings = Vector.distance(startOfArrow, endOfArrow) / 4;
		angleOfBodyOfArrow = Math.atan2(endOfArrow.getY() - startOfArrow.getY(), endOfArrow.getX() - startOfArrow.getX());
		
		page.setColor(color);
		
		//Body Of Arrow
		page.drawLine((int)startOfArrow.getX(), (int)startOfArrow.getY(), (int)endOfArrow.getX(), (int)endOfArrow.getY());
		
		//Wings Of Arrow
		vectorForEndOfWings.setPolar(lengthOfWings, angleOfBodyOfArrow + Math.PI*7 / 8);
		page.drawLine((int)endOfArrow.getX(), (int)endOfArrow.getY(), (int)(endOfArrow.getX() + vectorForEndOfWings.getX()), (int)(endOfArrow.getY() + vectorForEndOfWings.getY()));
		vectorForEndOfWings.setPolar(lengthOfWings, angleOfBodyOfArrow - Math.PI*7 / 8);
		page.drawLine((int)endOfArrow.getX(), (int)endOfArrow.getY(), (int)(endOfArrow.getX() + vectorForEndOfWings.getX()), (int)(endOfArrow.getY() + vectorForEndOfWings.getY()));
	}
}
