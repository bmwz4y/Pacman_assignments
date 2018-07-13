import java.awt.Color;
import java.awt.Graphics;

import util.Depth;

public class Pellet extends StaticEntity {
	private boolean large;
	
	public Pellet(int x, int y, boolean large) {
		super(x, y, Depth.MIDDLE);
		this.large = large;
	}
	
	@Override
	public void draw(Graphics g) {
		int r;
		
		g.setColor(Color.YELLOW);
		
		if (large){
			r = 7;
		}
		else{
			r = 3;
		}
		g.fillOval((this.getGridX()*Pacman.CELL_SIZE) + ((Pacman.CELL_SIZE/r)+1), (this.getGridY()*Pacman.CELL_SIZE) + ((Pacman.CELL_SIZE/r)+1), r*2, r*2);	
	}
	
}
