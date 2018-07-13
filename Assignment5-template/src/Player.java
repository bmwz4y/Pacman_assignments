import java.awt.Color;
import java.awt.Graphics;

import util.Depth;
import util.Direction;

public class Player extends DynamicEntity {
	public Player(GameContext context, int x, int y) {
		super(context, x, y, Depth.FRONT);
	}

	@Override
	public void update(Direction input) {
		move(input);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getX(), getY(), Pacman.CELL_SIZE, Pacman.CELL_SIZE);
	}
	
	@Override
	public void touched(Entity other) {
		if (other instanceof Ghost)
			((Ghost) other).touchedPlayer(this);
		else
			((Pellet) other).touchedPlayer(this);
	}
}
