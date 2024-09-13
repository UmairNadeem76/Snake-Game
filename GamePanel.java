package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private int moves = 0;
	private final int[] xlength = new int[750];
	private final int[] ylength = new int[750];
	private int initialLength = 2;
	private int score = 0;
	private int highscore = score;
	private int level = 1;
	private boolean up = false;
	private boolean down = false;
	private boolean right = true;
	private boolean left = false;

	private final int[] xpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 625, 650, 675, 700, 725, 750, 775, 800,
			825 };
	private final int[] ypos = { 75, 100, 125, 150, 175, 200, 225, 250, 425, 450, 475, 500, 525, 550, 575, 600, 625 };
	private final Timer timer;
	private final Timer timer1;
	private final int delay = 70;
	private final int delay1 = 80;
	private final ImageIcon s_Lmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
	private final ImageIcon s_Rmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
	private final ImageIcon s_UPmouth = new ImageIcon(getClass().getResource("upmouth.png"));
	private final ImageIcon s_DOWNmouth = new ImageIcon(getClass().getResource("downmouth.png"));
	private final ImageIcon food = new ImageIcon(getClass().getResource("snakefood.png"));

	private final Random random = new Random();
	private final Random random1 = new Random();
	private int foodx, foody, bonusfoodx, bonusfoody;;
	private boolean gameOver = false;
	private final ImageIcon body = new ImageIcon(getClass().getResource("snakeimage.png"));
	private final ImageIcon bonusfood = new ImageIcon(getClass().getResource("bonusfood.png"));
	private final ImageIcon obstacle = new ImageIcon(getClass().getResource("obstacle.png"));

	GamePanel() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay, this);
		timer1 = new Timer(delay1, this);
		timer.start();
		newfood();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(24, 10, 851, 56);
		g.setColor(Color.RED);
		g.drawRect(24, 10, 851, 56);
		g.drawRect(24, 74, 851, 576);

		g.setColor(Color.GRAY);
		g.fillRect(25, 11, 850, 55);
		if (level == 1) {
			g.setColor(Color.GRAY);
			g.fillRect(25, 75, 850, 575);
		}
		if (level == 2) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(25, 75, 850, 575);
		}
		if (level == 3) {
			g.setColor(Color.BLACK);
			g.fillRect(25, 75, 850, 575);
		}

		if (moves == 0) {
			xlength[0] = 100;
			xlength[1] = 75;
			ylength[0] = 100;
			ylength[1] = 100;

		}
		if (left) {
			s_Lmouth.paintIcon(this, g, xlength[0], ylength[0]);
		}
		if (right) {
			s_Rmouth.paintIcon(this, g, xlength[0], ylength[0]);
		}
		if (up) {
			s_UPmouth.paintIcon(this, g, xlength[0], ylength[0]);
		}
		if (down) {
			s_DOWNmouth.paintIcon(this, g, xlength[0], ylength[0]);
		}
		for (int i = 1; i < initialLength; i++) {
			body.paintIcon(this, g, xlength[i], ylength[i]);
		}
		if (score != 64 && score != 56 && score != 48 && score != 40 && score != 32 && score != 24 && score != 16
				&& score != 8) {
			food.paintIcon(this, g, foodx, foody);
		}
		if (score == 8) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 16) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 24) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 32) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 40) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 48) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 56) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score == 64) {
			bonusfood.paintIcon(this, g, bonusfoodx, bonusfoody);
		}
		if (score > 10) {
			obstacle.paintIcon(this, g, 250, 225);
			obstacle.paintIcon(this, g, 250, 250);
			obstacle.paintIcon(this, g, 250, 275);
			obstacle.paintIcon(this, g, 250, 300);
			obstacle.paintIcon(this, g, 250, 325);
			obstacle.paintIcon(this, g, 250, 350);
			obstacle.paintIcon(this, g, 350, 350);
			obstacle.paintIcon(this, g, 375, 350);
			obstacle.paintIcon(this, g, 400, 350);
			obstacle.paintIcon(this, g, 425, 350);
			obstacle.paintIcon(this, g, 450, 350);
			obstacle.paintIcon(this, g, 475, 350);
			obstacle.paintIcon(this, g, 500, 350);
			obstacle.paintIcon(this, g, 600, 225);
			obstacle.paintIcon(this, g, 600, 250);
			obstacle.paintIcon(this, g, 600, 275);
			obstacle.paintIcon(this, g, 600, 300);
			obstacle.paintIcon(this, g, 600, 325);
			obstacle.paintIcon(this, g, 600, 350);
			level = 2;
		}
		if (score > 20) {
			obstacle.paintIcon(this, g, 250, 450);
			obstacle.paintIcon(this, g, 250, 475);
			obstacle.paintIcon(this, g, 250, 500);
			obstacle.paintIcon(this, g, 250, 525);
			obstacle.paintIcon(this, g, 250, 550);
			obstacle.paintIcon(this, g, 250, 575);
			obstacle.paintIcon(this, g, 350, 450);
			obstacle.paintIcon(this, g, 375, 450);
			obstacle.paintIcon(this, g, 400, 450);
			obstacle.paintIcon(this, g, 425, 450);
			obstacle.paintIcon(this, g, 450, 450);
			obstacle.paintIcon(this, g, 475, 450);
			obstacle.paintIcon(this, g, 500, 450);
			obstacle.paintIcon(this, g, 600, 450);
			obstacle.paintIcon(this, g, 600, 475);
			obstacle.paintIcon(this, g, 600, 500);
			obstacle.paintIcon(this, g, 600, 525);
			obstacle.paintIcon(this, g, 600, 550);
			obstacle.paintIcon(this, g, 600, 575);
			level = 3;
		}
		if (score > 30) {
			obstacle.paintIcon(this, g, 275, 350);
			obstacle.paintIcon(this, g, 300, 350);
			obstacle.paintIcon(this, g, 325, 350);
			obstacle.paintIcon(this, g, 525, 350);
			obstacle.paintIcon(this, g, 550, 350);
			obstacle.paintIcon(this, g, 575, 350);
			obstacle.paintIcon(this, g, 275, 450);
			obstacle.paintIcon(this, g, 300, 450);
			obstacle.paintIcon(this, g, 325, 450);
			obstacle.paintIcon(this, g, 525, 450);
			obstacle.paintIcon(this, g, 550, 450);
			obstacle.paintIcon(this, g, 575, 450);
			level = 4;
		}
		if (gameOver) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("GAME OVER!", 275, 290);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Press SPACE BAR ", 350, 320);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("to restart the GAME!", 350, 340);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Your Score is " + score, 370, 395);

			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Maximum Level Reached: " + level, 310, 415);
			timer.stop();
			timer1.stop();
		}

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("SNAKE GAME", 325, 45);

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.ITALIC, 15));
		g.drawString("Score: " + score, 600, 60);

		g.setFont(new Font("Arial", Font.ITALIC, 15));
		g.drawString("Length of Snake: " + initialLength, 600, 30);

		g.setFont(new Font("Arial", Font.ITALIC, 15));
		g.drawString("Hi-Score: " + highscore, 750, 40);

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Level: " + level, 80, 45);

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			for (int i = initialLength - 1; i > 0; i--) {
				xlength[i] = xlength[i - 1];
				ylength[i] = ylength[i - 1];
			}

		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}

		if (left) {
			xlength[0] = xlength[0] - 25;
		}
		if (right) {
			xlength[0] = xlength[0] + 25;
		}
		if (up) {
			ylength[0] = ylength[0] - 25;
		}
		if (down) {
			ylength[0] = ylength[0] + 25;
		}
		if (xlength[0] > 850) {
			gameOver = true;
			timer.stop();
		}
		if (xlength[0] < 25) {
			gameOver = true;
			timer.stop();
		}
		if (ylength[0] > 625) {
			gameOver = true;
			timer.stop();
		}

		if (ylength[0] < 75) {
			gameOver = true;
			timer.stop();
		}
		eatingFood();
		collision();
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {
			pause();
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			resume();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT && !right || e.getKeyCode() == KeyEvent.VK_A && !right) {
			left = true;
			right = false;
			up = false;
			down = false;
			moves++;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left || e.getKeyCode() == KeyEvent.VK_D && !left) {
			left = false;
			right = true;
			up = false;
			down = false;
			moves++;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && !down || e.getKeyCode() == KeyEvent.VK_W && !down) {
			left = false;
			right = false;
			up = true;
			down = false;
			moves++;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && !up || e.getKeyCode() == KeyEvent.VK_S && !up) {
			left = false;
			right = false;
			up = false;
			down = true;
			moves++;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void pause() {
		timer.stop();
		timer1.stop();

	}

	private void resume() {
		timer.start();
		timer1.stop();

	}

	private void newfood() {
		foodx = xpos[random.nextInt(18)];
		foody = ypos[random.nextInt(17)];
		bonusfoodx = xpos[random1.nextInt(18)];
		bonusfoody = ypos[random1.nextInt(17)];

		for (int i = initialLength - 1; i >= 0; i--) {
			if (xlength[0] == foodx && ylength[0] == foody) {
				newfood();
			}
		}

	}

	private void eatingFood() {
		if (xlength[0] == foodx && ylength[0] == foody) {
			newfood();
			initialLength++;
			score++;
			if (score > highscore) {
				highscore = score;
			}
			if (score > 10 && score < 20) {
				timer.start();
			}
		}
		if (score == 8) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 2;
			}
		}
		if (score == 16) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 24) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 32) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 40) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 48) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 56) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
		if (score == 64) {
			if (xlength[0] == bonusfoodx && ylength[0] == bonusfoody) {
				newfood();
				initialLength = initialLength - 6;
				score = score + 3;
			}
		}
	}

	private void collision() {
		for (int i = initialLength - 1; i > 0; i--) {
			if (xlength[i] == xlength[0] && ylength[i] == ylength[0]) {
				timer.stop();
				gameOver = true;
			}
			if (score > 10) {
				if (xlength[0] == 250 && ylength[0] == 225 || xlength[0] == 250 && ylength[0] == 250
						|| xlength[0] == 250 && ylength[0] == 275 || xlength[0] == 250 && ylength[0] == 300
						|| xlength[0] == 250 && ylength[0] == 325 || xlength[0] == 250 && ylength[0] == 350) {
					timer.stop();
					gameOver = true;
				}
				if (xlength[0] == 350 && ylength[0] == 350 || xlength[0] == 375 && ylength[0] == 350
						|| xlength[0] == 400 && ylength[0] == 350 || xlength[0] == 425 && ylength[0] == 350
						|| xlength[0] == 450 && ylength[0] == 350 || xlength[0] == 475 && ylength[0] == 350) {
					timer.stop();
					gameOver = true;
				}
				if (xlength[0] == 500 && ylength[0] == 350 || xlength[0] == 600 && ylength[0] == 225
						|| xlength[0] == 600 && ylength[0] == 250 || xlength[0] == 600 && ylength[0] == 275
						|| xlength[0] == 600 && ylength[0] == 300 || xlength[0] == 600 && ylength[0] == 325
						|| xlength[0] == 600 && ylength[0] == 350) {
					timer.stop();
					gameOver = true;
				}
				if (score > 20) {
					if (xlength[0] == 250 && ylength[0] == 450 || xlength[0] == 250 && ylength[0] == 475
							|| xlength[0] == 250 && ylength[0] == 500 || xlength[0] == 250 && ylength[0] == 525
							|| xlength[0] == 250 && ylength[0] == 550 || xlength[0] == 250 && ylength[0] == 575) {
						timer.stop();
						gameOver = true;
					}

					if (xlength[0] == 350 && ylength[0] == 450 || xlength[0] == 375 && ylength[0] == 450
							|| xlength[0] == 400 && ylength[0] == 450 || xlength[0] == 425 && ylength[0] == 450
							|| xlength[0] == 450 && ylength[0] == 450 || xlength[0] == 475 && ylength[0] == 450) {
						timer.stop();
						gameOver = true;
					}

					if (xlength[0] == 500 && ylength[0] == 450 || xlength[0] == 600 && ylength[0] == 450
							|| xlength[0] == 600 && ylength[0] == 475 || xlength[0] == 600 && ylength[0] == 500
							|| xlength[0] == 600 && ylength[0] == 525 || xlength[0] == 600 && ylength[0] == 550
							|| xlength[0] == 600 && ylength[0] == 575) {
						timer.stop();
						gameOver = true;
					}
				}
			}
			if (score > 30) {
				if (xlength[0] == 275 && ylength[0] == 350 || xlength[0] == 300 && ylength[0] == 350
						|| xlength[0] == 325 && ylength[0] == 350 || xlength[0] == 525 && ylength[0] == 350
						|| xlength[0] == 550 && ylength[0] == 350 || xlength[0] == 575 && ylength[0] == 350) {
					timer.stop();
					gameOver = true;
				}

				if (xlength[0] == 275 && ylength[0] == 450 || xlength[0] == 300 && ylength[0] == 450
						|| xlength[0] == 325 && ylength[0] == 450 || xlength[0] == 525 && ylength[0] == 450
						|| xlength[0] == 550 && ylength[0] == 450 || xlength[0] == 575 && ylength[0] == 450) {
					timer.stop();
					gameOver = true;
				}
			}
		}

	}

	private void restart() {
		gameOver = false;
		moves = 0;
		score = 0;
		initialLength = 2;
		level = 1;
		up = false;
		down = false;
		left = false;
		right = true;
		timer.start();
		repaint();
	}
}