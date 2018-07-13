import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Depth;

public class Wall extends StaticEntity {
	static Image wallImage;
	static boolean fallback = false;

	public Wall(int x, int y) {
		super(x, y, Depth.BACK);
	}

	public boolean isSolid() {
		return true;
	}

	@Override
	public void draw(Graphics g) {
		if (fallback) {
			g.setColor(Color.RED);
			g.fillRect(getGridX() * Pacman.CELL_SIZE, getGridY() * Pacman.CELL_SIZE, Pacman.CELL_SIZE,
					Pacman.CELL_SIZE);
		} else {
			g.drawImage(wallImage, getGridX() * Pacman.CELL_SIZE, getGridY() * Pacman.CELL_SIZE, null);
		}
	}

	public static void loadImages() {
		try {
			File imageFile = new File("assets/" + "wall.png");
			wallImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			fallback = true;
			System.out.print(e.getMessage() + " for Wall");
		}
	}
}
