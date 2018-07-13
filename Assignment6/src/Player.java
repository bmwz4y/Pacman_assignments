import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Depth;
import util.Direction;

public class Player extends DynamicEntity {
	static Image pacmanEcImage;
	static Image pacmanEoImage;
	static Image pacmanNcImage;
	static Image pacmanNoImage;
	static Image pacmanScImage;
	static Image pacmanSoImage;
	static Image pacmanWcImage;
	static Image pacmanWoImage;
	static boolean fallback = false;
	private int moveCounter;
	private boolean openMouth;
	private int previousX = Pacman.playerInitX * Pacman.CELL_SIZE;
	private int previousY = Pacman.playerInitY * Pacman.CELL_SIZE;

	public Player(GameContext context, int x, int y) {
		super(context, x, y, Depth.FRONT);
	}

	@Override
	public void update(Direction input) {
		move(input);
	}

	@Override
	public void touched(Entity other) {
		other.touchedPlayer(this);
	}

	@Override
	public void draw(Graphics g) {
		if (fallback) {
			g.setColor(Color.YELLOW);
			g.fillRect(getX(), getY(), Pacman.CELL_SIZE, Pacman.CELL_SIZE);
		} else {
			if (previousX != getX() || previousY != getY()) {
				previousX = getX();
				previousY = getY();

				if (moveCounter / 10 == 0) {
					openMouth = true;
					moveCounter++;
				} else {
					openMouth = false;
					moveCounter++;
					if (moveCounter == 20)
						moveCounter = 0;
				}
			}
			else
			{
				openMouth = false;
			}

			switch (getFacing()) {
			case EAST:
				if (openMouth) {
					g.drawImage(pacmanEoImage, getX(), getY(), null);
				} else {
					g.drawImage(pacmanEcImage, getX(), getY(), null);
				}
				break;

			case NORTH:
				if (openMouth) {
					g.drawImage(pacmanNoImage, getX(), getY(), null);
				} else {
					g.drawImage(pacmanNcImage, getX(), getY(), null);
				}
				break;

			case SOUTH:
				if (openMouth) {
					g.drawImage(pacmanSoImage, getX(), getY(), null);
				} else {
					g.drawImage(pacmanScImage, getX(), getY(), null);
				}
				break;

			case WEST:
				if (openMouth) {
					g.drawImage(pacmanWoImage, getX(), getY(), null);
				} else {
					g.drawImage(pacmanWcImage, getX(), getY(), null);
				}
				break;
			}
		}
	}

	public static void loadImages() {
		try {
			File imageFile = new File("assets/" + "pacman-e1.png");
			pacmanEcImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-e2.png");
			pacmanEoImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-n1.png");
			pacmanNcImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-n2.png");
			pacmanNoImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-s1.png");
			pacmanScImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-s2.png");
			pacmanSoImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-w1.png");
			pacmanWcImage = ImageIO.read(imageFile);

			imageFile = new File("assets/" + "pacman-w2.png");
			pacmanWoImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			fallback = true;
			System.out.print(e.getMessage() + " for Player");
		}
	}
}
