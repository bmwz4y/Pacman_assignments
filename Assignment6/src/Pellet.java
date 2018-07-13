import java.awt.Color;
import java.awt.Graphics;

import util.Depth;

public class Pellet extends StaticEntity {
	private GameContext context;
	private boolean large;

	public Pellet(GameContext context, int x, int y, boolean large) {
		super(x, y, Depth.MIDDLE);
		this.context = context;
		this.large = large;
	}

	@Override
	public void touchedPlayer(Player player) {
		context.getEntities().removeEntity(this);
	}

	@Override
	public void draw(Graphics g) {
		int r = large ? 7 : 3;
		int x = getGridX() * Pacman.CELL_SIZE + Pacman.CELL_SIZE / 2 - r;
		int y = getGridY() * Pacman.CELL_SIZE + Pacman.CELL_SIZE / 2 - r;

		g.setColor(Color.YELLOW);
		g.fillOval(x, y, r * 2, r * 2);
	}
}
