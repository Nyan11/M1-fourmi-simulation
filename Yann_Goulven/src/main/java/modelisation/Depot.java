package modelisation;

import modelisation.utils.Position;
import modelisation.utils.Simulable;

public class Depot implements Simulable {

	private Position position;
	
	public Depot(Position position) {
		int x = (int)(Math.random() * 30) - 15;
		int y = (int)(Math.random() * 30) - 15;
		this.position = new Position(position.x() - x, position.y() - y);
	}

	@Override
	public void step(Terrain parent) { }
	
	public Position getPosition() {
		return this.position;
	}

}
