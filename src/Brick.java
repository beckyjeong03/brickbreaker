import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import csta.ibm.pong.GameObject;
/**
 * ICS4U
 * FINAL PROJECT BECKY JEONG
 * BRICK CLASS
 * @author jeongseon-u
 */


/**
 * Brick class defines the characteristics of the brick game object. The brick
 * objects get removed if they interact with a ball with certain conditions
 * 
 * @author jeongseon-u
 *
 */
public class Brick extends GameObject {

	// set value of the brick size
	private final static int BRICK_WIDTH = 70;
	private final static int BRICK_HEIGHT = 40;
	private int numberHits;

	/**
	 * This method changes the behaviour and characteristics of the brick every time
	 * fram
	 */
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	/**
	 * Constructor for bricks. defines x and y position and colour
	 * 
	 * @param x
	 * @param y
	 * @param c
	 */
	public Brick(int x, int y, Color c) {
		setX(x);
		setY(y);

		setColor(c);
		setSize(BRICK_WIDTH, BRICK_HEIGHT);
	}

	/**
	 * Constructor for bricks. defines x and y position, colour as well as width and
	 * height
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param width
	 * @param height
	 */
	public Brick(int x, int y, Color c, int width, int height) {
		setX(x);
		setY(y);
		setColor(c);
		setSize(width, height);
	}

	/**
	 * Constructor for bricks. defines x and y position, colour, width and height as
	 * well as number of times hit
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param width
	 * @param height
	 * @param numHits
	 */
	public Brick(int x, int y, Color c, int width, int height, int numHits) {
		setX(x);
		setY(y);
		setColor(c);
		setSize(width, height);
		numberHits = numHits;
	}

	/**
	 * return the numberHits
	 * 
	 * @return
	 */
	public int getNumHits() {
		return numberHits;
	}

	/**
	 * set the numberHits
	 * 
	 * @param n
	 */
	public void setNumHits(int n) {
		numberHits = n;
	}

}
