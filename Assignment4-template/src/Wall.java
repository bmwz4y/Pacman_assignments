import java.awt.Color;
import java.awt.Graphics;

import util.Depth;

public class Wall extends StaticEntity {

	public Wall(int x, int y) {
		super(x, y, Depth.BACK);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		super.draw(g);
	}
}
