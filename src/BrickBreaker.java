import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;




//import Pong.State;
import csta.ibm.pong.Game;
/*
 * HELPFUL URLS
 * Collision detection:
 * https://www.youtube.com/watch?v=1_H7InPMjaY
 * https://zetcode.com/javagames/collision/
 * JOptionPane:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html#:~:text=JOptionPane%20makes%20it%20easy%20to,section%20in%20The%20Java%20Tutorial.&text=Each%20showXxxDialog%20method%20blocks%20the%20caller%20until%20the%20user's%20interaction%20is%20complete.
 * OTHERS:
 * https://docs.oracle.com/javase/7/docs/api/
 * https://docs.oracle.com/javase/7/docs/api/java/awt/Rectangle.html
 * https://www.tutorialspoint.com/java/number_abs.htm
 * https://www.youtube.com/watch?v=K9qMm3JbOH0
 */

/**
 * ICS4U
 * FINAL PROJECT BECKY JEONG
 * BRICKBREAKER CLASS
 * @author jeongseon-u
 */
public class BrickBreaker extends Game {

	// constant
	private final static int HEIGHT = 556;
	private final static int WIDTH = 830;
	private final static int OFFSCREEN = 1000;
	private final static int BRICK_WIDTH = 70;
	private final static int BRICK_HEIGHT = 40;

	// variables that changes throughout the game
	private static int paddleLength = 100;
	private static int ballSize = 20;
	private static int totalBrick = 66;
	private static int powerUpHit = 1;

	// classes / objects
	private Paddle paddle;
	private Ball ball;

	// enum
	private static enum State {
		MENU, GAME
	};

	private static State state;

	// JLabels
	private JLabel brickLeft;
	private JLabel ballImage;
	private JLabel ball2Image;
	private JLabel ball3Image;
	private JLabel paddleImage;
	private JLabel paddle2Image;
	private JLabel paddle3Image;

	// ImageIcons
	private ImageIcon b1 = new ImageIcon("ball.png");
	private ImageIcon b2 = new ImageIcon("ball2.png");
	private ImageIcon b3 = new ImageIcon("ball3.png");
	private ImageIcon p1 = new ImageIcon("paddle.png");
	private ImageIcon p2 = new ImageIcon("paddle2.png");
	private ImageIcon p3 = new ImageIcon("paddle3.png");
	private ImageIcon pu = new ImageIcon("power_up.png");
	private ImageIcon th = new ImageIcon("two_hits.png");
	private ImageIcon wb = new ImageIcon("whitebrick.png");

	// declaring ArrayLists
	private final int BRICK_NUM = 55;
	private final int POWER_UP_NUM = 2;
	private final int DOUBLE_HIT_NUM = 11;
	private ArrayList<Brick> bricks = new ArrayList<Brick>(BRICK_NUM);
	private ArrayList<JLabel> brickImage = new ArrayList<JLabel>(BRICK_NUM);
	private ArrayList<Brick> powerUps = new ArrayList<Brick>(POWER_UP_NUM);
	private ArrayList<JLabel> powerUpBrickImage = new ArrayList<JLabel>(POWER_UP_NUM);
	private ArrayList<Brick> doubleHits = new ArrayList<Brick>(DOUBLE_HIT_NUM);
	private ArrayList<JLabel> doubleHitsBrickImage = new ArrayList<JLabel>(DOUBLE_HIT_NUM);
	private ArrayList<JLabel> finalHit = new ArrayList<JLabel>(DOUBLE_HIT_NUM);

	/**
	 * Main method that was given; no modification was made
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BrickBreaker p = new BrickBreaker();
		p.setVisible(true);
		p.initComponents();
		p.setSize(830, 600);

	}

	/**
	 * When the game begins, this method will automatically be executed every
	 * millisecond This may be used as a control method for checking user input and
	 * collision between any game objects
	 */
	@Override
	public void act() {
		if (state == State.GAME) {
			// set the position of all types of ball images to the same position as the ball
			// object
			ballImage.setLocation(ball.getX(), ball.getY());
			ball2Image.setLocation(ball.getX(), ball.getY());
			ball3Image.setLocation(ball.getX(), ball.getY());

			// set the position of all types of paddle images to the same position as the
			// paddle object
			paddleImage.setLocation(paddle.getX(), paddle.getY());
			paddle2Image.setLocation(paddle.getX(), paddle.getY());
			paddle3Image.setLocation(paddle.getX(), paddle.getY());

			// call methods
			keyPressed();
			checkCollision();
			winOrLose();
			repaint();

		}

	}

	/**
	 * When implemented, this will allow the programmer to initialize the game
	 * before it begins running Adding objects to the game and setting their initial
	 * positions should be done here.
	 */
	@Override
	public void setup() {
		// TODO Auto-generated method stub

		setDelay(3);

		// declaring the paddle object
		paddle = new Paddle((WIDTH - paddleLength) / 2, HEIGHT - 30, paddleLength, 10);
		add(paddle);

		// declaring the ball object
		ball = new Ball(WIDTH / 2, HEIGHT / 2, Color.WHITE, ballSize);
		add(ball);

		// set the state as menu -> game doesn't start
		state = State.MENU;

		// menu deplays
		displayMainMenu();

		// start with the basic ball images
		ballImage = new JLabel(b1);
		ballImage.setBounds(ball.getX(), ball.getY(), ballSize, ballSize);
		ballImage.setLocation(ball.getX(), ball.getY());
		add(ballImage);
		ball.setVisible(false);

		// set the ball2 images without visibility
		ball2Image = new JLabel(b2);
		ball2Image.setBounds(ball.getX(), ball.getY(), ballSize + 3, ballSize + 3);
		ball2Image.setLocation(ball.getX(), ball.getY());
		add(ball2Image);
		ball2Image.setVisible(false);

		// set the ball3 images without visibility
		ball3Image = new JLabel(b3);
		ball3Image.setBounds(ball.getX(), ball.getY(), ballSize + 6, ballSize + 6);
		ball3Image.setLocation(ball.getX(), ball.getY());
		add(ball3Image);
		ball3Image.setVisible(false);

		// Start with the basic paddle images
		paddleImage = new JLabel(p1);
		paddleImage.setBounds(paddle.getX(), paddle.getY(), paddleLength, 10);
		paddleImage.setLocation(paddle.getX(), paddle.getY());
		add(paddleImage);
		paddle.setVisible(false);

		// set the paddle2 images without visibility
		paddle2Image = new JLabel(p2);
		paddle2Image.setBounds(paddle.getX(), paddle.getY(), paddleLength + 50, 10);
		paddle2Image.setLocation(paddle.getX(), paddle.getY());
		add(paddle2Image);
		paddle2Image.setVisible(false);

		// set the paddle3 images without visibility
		paddle3Image = new JLabel(p3);
		paddle3Image.setBounds(paddle.getX(), paddle.getY(), paddleLength + 100, 10);
		paddle3Image.setLocation(paddle.getX(), paddle.getY());
		add(paddle3Image);
		paddle3Image.setVisible(false);

		// display the number of brick left on the screen
		brickLeft = new JLabel();
		brickLeft.setText("" + totalBrick);
		brickLeft.setForeground(Color.WHITE);
		brickLeft.setBounds(5, HEIGHT - 200, 200, 300);
		brickLeft.setFont(new Font("Verdana", Font.BOLD, 50));
		add(brickLeft);

		// call methods
		doubleHit();
		powerUp();
		setBricks();

	}

	/**
	 * Move paddle according to the keyboard input
	 */
	public void keyPressed() {

		// when key "N" is pressed, the paddle move left
		if (NKeyPressed() && paddle.getX() > 0) {
			paddle.moveLeft();
		}

		// when key "M" is pressed, the paddle move right
		if (MKeyPressed() && paddle.getX() < WIDTH - paddleLength) {
			paddle.moveRight();
		}
	}

	/**
	 * Display the main menu with options; Play, Rules, Exit
	 */
	public void displayMainMenu() {
		Object[] options = { "Play", "Rules", "Exit" };
		ImageIcon icon = new ImageIcon("title.jpeg");

		// display a option pane with various buttons
		int option = JOptionPane.showOptionDialog(null, "Welcome to Brick Breaker!", "MAIN MANU",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

		if (option == 0) {
			state = State.GAME;// if the play button is clicked
		} else if (option == 1) { // if the rules button is clicked
			displayRules();

		} else if (option == 2) {// if the exit button is clicked
			System.exit(1);
		}
		// close the game if the x is clicked
		if (option == JOptionPane.CLOSED_OPTION) {
			System.exit(1);
		}
	}

	/**
	 * This method displays the game instructions in a JOptionPane.
	 */
	public void displayRules() {
		ImageIcon icon = new ImageIcon("rules.png");

		JOptionPane.showMessageDialog(null, "[RULES]\n" + "WHITE(BLUE) BRICK: Hit it once.\n"
				+ "PINK BRICK: Power Up -> longer paddle and bigger ball.\n" + "YELLOW BRICK: Hit it twice.\n"
				+ "There are total of 66 bricks. Break all the bricks to win! \nYou may resume or restart the game once you die.\n"
				+ "Your powerUp ability will be taken away once you resume or restart.\n"
				+ "CONTROLS: Press \"N\" key to move paddle to the left, press \"M\" Key to move the paddle to the right.",
				"RULES", 3, icon);
		displayMainMenu(); // goes back to the main menu
	}

	/**
	 * Check collision that are happening throughout the Game
	 */
	public void checkCollision() {
		// hit wall
		if (ball.getY() <= 0) {
			System.out.println("hit wall");
			ball.setTrajY(ball.getTrajY() * (-1));
		}
		// hit wall
		if (ball.getX() <= 0 || ball.getX() >= WIDTH - ballSize) {
			System.out.println("hit wall");
			ball.setTrajX(ball.getTrajX() * (-1));
		}
		// hit paddle
		if (ball.collides(paddle)) {
			getPaddleBounceAngle();
			System.out.println("hit paddle");
		}

		int collisionTolerance = 10;

		// loop
		A: for (int j = 0; j <= BRICK_NUM; j++) {

			// when the ball hits two bricks horizontally at the same time
			if (j < BRICK_NUM && ball.collides(bricks.get(j)) && ball.collides(bricks.get(j + 1))) {

				// brick botton ball top collision
				if (Math.abs((bricks.get(j).getY() + BRICK_HEIGHT) - ball.getY()) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());
				}
				// brick top ball bottom collision
				if (Math.abs(bricks.get(j).getY() - (ball.getY() + ballSize)) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());
				}
				// brick right ball left collision
				if (Math.abs((bricks.get(j).getX() + BRICK_WIDTH - ball.getX())) < collisionTolerance - 5) {
					ball.setTrajX(-ball.getTrajX());
				}
				// brick left ball right collision
				if (Math.abs((bricks.get(j).getX() - ball.getX() + ballSize)) < collisionTolerance - 5) {
					ball.setTrajX(-ball.getTrajX());
				}

				// removing bricks from the frame
				bricks.get(j).setVisible(false);
				brickImage.get(j).setVisible(false);
				bricks.get(j).setX(WIDTH + 1000);
				totalBrick--;
				brickLeft.setText("" + totalBrick);
				System.out.println("" + totalBrick);
				bricks.get(j).setY(HEIGHT + 1000);

				// stop the loop
				break A;

				// when the ball hits two bricks vertically at the same time
			} else if (j > 11 && ball.collides(bricks.get(j)) && ball.collides(bricks.get(j - 11))) {

				if (Math.abs((bricks.get(j).getY() + BRICK_HEIGHT) - ball.getY()) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());
				}
				if (Math.abs(bricks.get(j).getY() - (ball.getY() + ballSize)) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());
				}
				if (Math.abs((bricks.get(j).getX() + BRICK_WIDTH - ball.getX())) < collisionTolerance) {
					ball.setTrajX(-ball.getTrajX());
				}
				if (Math.abs((bricks.get(j).getX() - ball.getX() + ballSize)) < collisionTolerance) {
					ball.setTrajX(-ball.getTrajX());
				}

				bricks.get(j).setVisible(false);
				brickImage.get(j).setVisible(false);
				bricks.get(j).setX(WIDTH + 1000);
				totalBrick--;
				brickLeft.setText("" + totalBrick);
				System.out.println("" + totalBrick);
				bricks.get(j).setY(HEIGHT + 1000);

				// stop the loop
				break A;

				// when the ball hits only one block
			} else if (ball.collides(bricks.get(j))) {

				if (Math.abs((bricks.get(j).getY() + BRICK_HEIGHT) - ball.getY()) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());

					// brick top ball botton
				} else if (Math.abs(bricks.get(j).getY() - (ball.getY() + ballSize)) < collisionTolerance) {
					ball.setTrajY(-ball.getTrajY());

					// brick right ball left
				} else if (Math.abs((bricks.get(j).getX() + BRICK_WIDTH - ball.getX())) < collisionTolerance) {
					ball.setTrajX(-ball.getTrajX());

					// brick left ball right
				} else if (Math.abs((bricks.get(j).getX() - (ball.getX() + ballSize))) < collisionTolerance) {
					ball.setTrajX(-ball.getTrajX());
				}

				bricks.get(j).setVisible(false);
				brickImage.get(j).setVisible(false);
				bricks.get(j).setX(WIDTH + 1000);
				totalBrick--;
				brickLeft.setText("" + totalBrick);
				System.out.println("" + totalBrick);
				bricks.get(j).setY(HEIGHT + 1000);

			}
		}
		// collision check with doublehits and powerups
		collisionWithDoubleHits();
		checkCollisionWithPowerUp();

	}

	/**
	 * check collision with double hits blocks
	 */
	public void collisionWithDoubleHits() {
		int collisionTolerance = 10;

		B: for (int p = 0; p < DOUBLE_HIT_NUM; p++) {
			// when ball collides with doubleHits blocks

			if (ball.collides(doubleHits.get(p))) {

				// when the ball hit the doubleHit block once
				if (doubleHits.get(p).getNumHits() == 2) {
					if (Math.abs((doubleHits.get(p).getY() + BRICK_HEIGHT) - ball.getY()) < collisionTolerance) {
						ball.setTrajY(-ball.getTrajY());
					}
					if (Math.abs(doubleHits.get(p).getY() - (ball.getY() + ballSize)) < collisionTolerance) {
						ball.setTrajY(-ball.getTrajY());
					}
					if (Math.abs((doubleHits.get(p).getX() + BRICK_WIDTH - ball.getX())) < collisionTolerance-5) {
						ball.setTrajX(-ball.getTrajX());
					}
					if (Math.abs((doubleHits.get(p).getX() - ball.getX() + ballSize)) < collisionTolerance-5) {
						ball.setTrajX(-ball.getTrajX());
					}
					System.out.println("once");
					doubleHitsBrickImage.get(p).setVisible(false);
					finalHit.get(p).setVisible(true);
					doubleHits.get(p).setNumHits(1);
					break B;

					// when the ball hit the doubleHit block twice
				} else if (doubleHits.get(p).getNumHits() == 1) {
					if (Math.abs((doubleHits.get(p).getY() + BRICK_HEIGHT) - ball.getY()) < collisionTolerance) {
						ball.setTrajY(-ball.getTrajY());
					}
					if (Math.abs(doubleHits.get(p).getY() - (ball.getY() + ballSize)) < collisionTolerance) {
						ball.setTrajY(-ball.getTrajY());
					}
					if (Math.abs((doubleHits.get(p).getX() + BRICK_WIDTH - ball.getX())) < collisionTolerance) {
						ball.setTrajX(-ball.getTrajX());
					}
					if (Math.abs((doubleHits.get(p).getX() - ball.getX() + ballSize)) < collisionTolerance) {
						ball.setTrajX(-ball.getTrajX());
					}
					doubleHits.get(p).setNumHits(0);
					// doubleHits.get(p).setVisible(false);
					finalHit.get(p).setVisible(false);
					// removing it from the frame
					doubleHits.get(p).setX(WIDTH + 1000);
					totalBrick--;
					System.out.println("" + totalBrick);
					doubleHits.get(p).setY(HEIGHT + 1000);
					System.out.println("twice");
					brickLeft.setText("" + totalBrick);
					break B;

				}

			}

		}
	}

	/**
	 * Check collision with powerUp blocks
	 */
	public void checkCollisionWithPowerUp() {

		// loop
		for (int i = 0; i < POWER_UP_NUM; i++) {
			if (ball.collides(powerUps.get(i))) {
				// remove powerup images
				powerUps.get(i).setVisible(false);
				powerUpBrickImage.get(i).setVisible(false);

				// removing it from the frame
				powerUps.get(i).setX(WIDTH + OFFSCREEN);
				powerUps.get(i).setY(HEIGHT + OFFSCREEN);

				paddleLength = paddleLength + 50;
				ballSize = ballSize + 3;
				ball.setBallSize(ballSize);
				paddle.setLength(paddleLength);

				// changing images depending on the number of times the player hit the powerUp
				// bricks
				if (powerUpHit == 1) {
					paddle2Image.setVisible(true);
					paddleImage.setVisible(false);
					ball2Image.setVisible(true);
					ballImage.setVisible(false);
					powerUpHit++;
				} else if (powerUpHit == 2) {
					paddle3Image.setVisible(true);
					paddle2Image.setVisible(false);
					ball3Image.setVisible(true);
					ball2Image.setVisible(false);
					ballImage.setVisible(false);

				}

			}

		}

	}

	/**
	 * set Bricks
	 */
	public void setBricks() {
		// declare local variables
		final int SPACE = 75;
		int x = 5;
		int y = BRICK_HEIGHT + 10;
		setVisible(true);

		for (int j = 0; j <= BRICK_NUM; j++) {
			// loop through the bricks, constructing them, setting their location, as well
			// as their images
			bricks.add(new Brick(x, y, Color.WHITE));
			brickImage.add(new JLabel(wb));
			brickImage.get(j).setBounds(bricks.get(j).getX(), bricks.get(j).getY(), BRICK_WIDTH, BRICK_HEIGHT);
			add(brickImage.get(j));
			add(bricks.get(j));
			brickImage.get(j).setVisible(true);
			bricks.get(j).setVisible(false);

			x += SPACE; // move the next brick over so that they don't overlap
			if (j % 11 == 0 && j != 0) { // every 11 bricks, start a new row
				y += BRICK_HEIGHT + 5;
				x = 5;

			}
		}

		// removing excess brick
		bricks.get(11).setX(WIDTH + OFFSCREEN);
	}

	/**
	 * create blocks with powerUp ability -> longer paddle, bigger ball
	 */
	public void powerUp() {
		// local variables
		int random1;
		int random2;
		int x, y;
		int x1 = 0, y1 = 0;
		for (int i = 0; i < POWER_UP_NUM; i++) {

			// create random positions for powerUps
			random1 = (int) (Math.random() * 11);
			random2 = (int) (Math.random() * 5 + 1);
			x = 5 + (random1 * (BRICK_WIDTH + 5));
			y = 5 + (random2 * (BRICK_HEIGHT + 5));

			// make sure they don't overlap with each other
			if (x != x1 && y != y1) {

				powerUps.add(new Brick(x, y, Color.PINK));
				powerUpBrickImage.add(new JLabel(pu));
				powerUpBrickImage.get(i).setBounds(powerUps.get(i).getX(), powerUps.get(i).getY(), BRICK_WIDTH,
						BRICK_HEIGHT);

				add(powerUpBrickImage.get(i));
				add(powerUps.get(i));

				powerUps.get(i).setVisible(false);
				powerUpBrickImage.get(i).setVisible(true);

				x1 = x;
				y1 = y;
			} else {
				// if overlap do it again until they don't
				i--;

			}

		}

	}

	/**
	 * create blocks that require two hits
	 */
	public void doubleHit() {
		// declare local variables
		final int SPACE = 75;
		int x = 5;
		int y = 5;

		for (int i = 0; i < DOUBLE_HIT_NUM; i++) {
			doubleHits.add(new Brick(x, y, Color.YELLOW, 70, 40, 2));
			add(doubleHits.get(i));
			doubleHits.get(i).setVisible(false);

			// when they are not hit
			doubleHitsBrickImage.add(new JLabel(th));
			doubleHitsBrickImage.get(i).setBounds(doubleHits.get(i).getX(), doubleHits.get(i).getY(), BRICK_WIDTH,
					BRICK_HEIGHT);
			add(doubleHitsBrickImage.get(i));
			doubleHitsBrickImage.get(i).setVisible(true);

			// when they get hit once
			finalHit.add(new JLabel(wb));
			finalHit.get(i).setBounds(doubleHits.get(i).getX(), doubleHits.get(i).getY(), BRICK_WIDTH, BRICK_HEIGHT);
			add(finalHit.get(i));
			finalHit.get(i).setVisible(false);

			x += SPACE; // move the next brick over so that they don't overlap

		}

	}

	/**
	 * display if they won or not with options they can pick
	 */
	public void winOrLose() {

		// when no brick left
		if (totalBrick == 0) {
			paddle.setPaddleSpeed(0);
			ImageIcon happyIcon = new ImageIcon("happy.png");

			int c = JOptionPane.showConfirmDialog(null, "YOU WON!!\nWanna play again?", "You won!",
					JOptionPane.YES_NO_OPTION, 2, happyIcon);

			if (c == JOptionPane.YES_OPTION) {
				resetGame();
			} else {
				ImageIcon sadIcon = new ImageIcon("sad.png");
				JOptionPane.showMessageDialog(null, "Goodbye", "BYEBYE", 1, sadIcon);
				System.exit(0);
			}
		}

		// when the ball goes outside of the bottom frame with bricks left on screen
		if (ball.getY() > HEIGHT && totalBrick != 0) {
			paddle.setPaddleSpeed(0);
			ImageIcon sadIcon = new ImageIcon("sad.png");

			System.out.println("you lost");
			Object[] options = { "Restart", "Resume", "Exit" };

			// display a option pane with various buttons
			int option = JOptionPane.showOptionDialog(null, "YOU LOST!!\n" + totalBrick + " bricks left", "YOU LOST",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, sadIcon, options, options[0]);

			if (option == 0) {// restart
				resetGame();

			} else if (option == 1) { // resume
				resumeGame();

			} else if (option == 2) {// exit
				System.exit(1);
			}

		}

	}

	/**
	 * Changes the trajactory of the ball depending on which section of the paddle
	 * it collides with
	 */
	public void getPaddleBounceAngle() {
		int changeAnglePaddleSection = (int) (paddle.getWidth() * 0.2); // the paddle is split into 20% chunks

		// the following decision structure determines how the angle of the ball and how
		// fast it should go (depending on the difficulty setting)
		if (ball.getX() >= paddle.getX() + (changeAnglePaddleSection * 4)) { // sharp right
			ball.setTrajX(2);
			ball.setTrajY(-1);

		} else if (ball.getX() >= paddle.getX() + (changeAnglePaddleSection * 3)) { // moderate right
			ball.setTrajX(1);
			ball.setTrajY(-1);

		} else if (ball.getX() >= paddle.getX() + (changeAnglePaddleSection * 2)) { // straight up
			ball.setTrajX(0);
			ball.setTrajY(-2);

		} else if (ball.getX() >= paddle.getX() + changeAnglePaddleSection) { // moderate left
			ball.setTrajX(-1);
			ball.setTrajY(-1);

		} else if (ball.getX() >= paddle.getX() - 15) { // sharp left
			ball.setTrajX(-2);
			ball.setTrajY(-1);

		}

	}

	/**
	 * Restart game. Resets blocks, powerUps, doubleHits, paddle position and ball
	 * position
	 */
	public void resetGame() {
		// resetting bricks
		totalBrick = 66;
		setBricks();
		for (int i = 0; i < bricks.size(); i++) { // move the existing bricks off screen
			bricks.get(i).setX(WIDTH + OFFSCREEN);
			bricks.get(i).setY(HEIGHT + OFFSCREEN);
		}
		for (int i = 0; i < POWER_UP_NUM; i++) {
			powerUps.get(i).setX(WIDTH + OFFSCREEN);
			powerUps.get(i).setY(HEIGHT + OFFSCREEN);
		}
		for (int i = 0; i < DOUBLE_HIT_NUM; i++) {
			doubleHits.get(i).setX(WIDTH + OFFSCREEN);
			doubleHits.get(i).setY(HEIGHT + OFFSCREEN);
		}

		// Reset the arrays
		ArrayList<Brick> powerUps = new ArrayList<Brick>(BRICK_NUM);
		this.powerUps = powerUps;

		ArrayList<Brick> bricks = new ArrayList<Brick>(BRICK_NUM);
		this.bricks = bricks;

		ArrayList<Brick> doubleHits = new ArrayList<Brick>(BRICK_NUM);
		this.doubleHits = doubleHits;

		// reset powerUps, doubleHits, bricks
		powerUp();
		doubleHit();
		setBricks();

		// reset ball position and trajectory
		ball.setX(WIDTH / 2);
		ball.setY(HEIGHT / 2);
		ball.setTrajX(0);
		ball.setTrajY(2);

		// reset paddle length and ball size, doubleHit value
		paddleLength = 100;
		paddle.setLength(paddleLength);
		ballSize = 20;
		ball.setSize(ballSize, ballSize);
		paddle.setPaddleSpeed(5);
		powerUpHit = 1;

		// reset paddle speed
		resetPaddleSpeed();
		paddle.setPaddleSpeed(5);

		// reset paddle position
		paddle.setX((WIDTH - paddleLength) / 2);
		paddle.setY(HEIGHT - 30);

		// reset image visibility
		paddle3Image.setVisible(false);
		paddle2Image.setVisible(false);
		paddleImage.setVisible(true);
		ball3Image.setVisible(false);
		ball2Image.setVisible(false);
		ballImage.setVisible(true);
	}

	/**
	 * resume game without resetting the bricks. The powerup disappears
	 */
	public void resumeGame() {

		// reset ball, paddle positions, sizes, images, speed and trajectory
		// however, bricks remain the same
		ball.setX(WIDTH / 2);
		ball.setY(HEIGHT / 2);
		ball.setTrajX(0);
		ball.setTrajY(1);
		paddleLength = 100;
		powerUpHit = 1;
		paddle.setX((WIDTH - paddleLength) / 2);
		paddle.setY(HEIGHT - 30);
		paddle.setLength(paddleLength);
		ballSize = 20;
		resetPaddleSpeed();
		paddle.setPaddleSpeed(5);
		ball.setSize(ballSize, ballSize);
		paddle3Image.setVisible(false);
		paddle2Image.setVisible(false);
		paddleImage.setVisible(true);
		ball3Image.setVisible(false);
		ball2Image.setVisible(false);
		ballImage.setVisible(true);

	}

}
