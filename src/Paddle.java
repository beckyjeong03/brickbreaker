import csta.ibm.pong.GameObject;
/**
 * ICS4U
 * FINAL PROJECT BECKY JEONG
 * PADDLE CLASS
 * @author jeongseon-u
 */

/**
 * Paddle class defines the characteristics and behaviour of the paddle game
 * object. The paddle objects changes the direction of the ball when interactes
 * with ball.
 * 
 * @author jeongseon-u
 *
 */
public class Paddle extends GameObject {

	// variables for paddle speed and size
	private static int PADDLE_SPEED = 5;
	private static int PADDLE_SIZE_Y, PADDLE_SIZE_X;

	/**
	 * Constructor for paddle. Defines x and y position, and size of the paddle.
	 * 
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 */
	public Paddle(int x, int y, int sizeX, int sizeY) {
		setX(x);
		setY(y);
		PADDLE_SIZE_Y = sizeY;
		PADDLE_SIZE_X = sizeX;
		setSize(PADDLE_SIZE_X, PADDLE_SIZE_Y);

	}

	/**
	 * This method is used every time frame during the game
	 */
	// @Override
	public void act() {

	}

	/**
	 * move paddle to the right
	 */
	public void moveRight() {
		setX(getX() + PADDLE_SPEED);

	}

	/**
	 * move paddle to the left
	 */
	public void moveLeft() {
		setX(getX() - PADDLE_SPEED);
	}

	/**
	 * set the paddle length to n value
	 * 
	 * @param n
	 */
	public void setLength(int n) {
		setSize(n, PADDLE_SIZE_X);
	}

	/**
	 * set the paddle speed to n value
	 * 
	 * @param n
	 */
	public void setPaddleSpeed(int n) {
		PADDLE_SPEED = n;
	}

}
