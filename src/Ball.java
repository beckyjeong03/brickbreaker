import java.awt.Color;

import csta.ibm.pong.GameObject;
/**
 * ICS4U
 * FINAL PROJECT BECKY JEONG
 * BALL CLASS
 * @author jeongseon-u
 */



/**
 * Ball class defines the characteristics and behavior of the ball game object.
 * The ball object changes direction and interacts with paddle and different
 * types of bricks removes brick and retrieves special abilities
 * 
 * @author jeongseon-u
 *
 */
public class Ball extends GameObject {
	private static double speed = 1.0;
	private double traj_x = 0;
	private double traj_y = 2;

	/**
	 * Constructor for ball. It defines colour, x and y position as well as size
	 * 
	 * @param x
	 * @param y
	 * @param color
	 * @param size
	 */
	public Ball(int x, int y, Color color, int size) {
		setColor(color);
		setX(x);
		setY(y);
		setSize(size, size);
		// setVisible(true);

		// setVisible(false);
	}

	/**
	 * this method is used every time frame during the game
	 */
	@Override
	public void act() {
		setX((int) (getX() + speed * traj_x));
		setY((int) (getY() + speed * traj_y));
	}

	/**
	 * This method returns value of the x trajectory of a ball
	 * 
	 * @param x
	 */
	public void setTrajX(double x) {
		traj_x = x;
	}

	/**
	 * this method returns value of the y trajectory of a ball
	 * 
	 * @param x
	 */
	public void setTrajY(double x) {
		traj_y = x;
	}

	/**
	 * This value return X trajectory
	 * 
	 * @return
	 */
	public double getTrajX() {
		return traj_x;
	}

	/**
	 * This method returns Y trajectory
	 * 
	 * @return
	 */
	public double getTrajY() {
		return traj_y;
	}

	/**
	 * This method re-define the size of the ball
	 * 
	 * @param n
	 */
	public void setBallSize(int n) {
		setSize(n, n);
	}

}
