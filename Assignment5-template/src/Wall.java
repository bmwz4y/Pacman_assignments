import java.awt.Color;
import java.awt.Graphics;

import util.Depth;

public class Wall extends StaticEntity {
	public Wall(int x, int y) {
		super(x, y, Depth.BACK);
	}

	public boolean isSolid() {
		return true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getGridX() * Pacman.CELL_SIZE, getGridY() * Pacman.CELL_SIZE, Pacman.CELL_SIZE, Pacman.CELL_SIZE);
	}
}
