package modelisation.pheromone;

import java.util.List;

import modelisation.Terrain;
import modelisation.utils.Position;

public class PheromoneRecherche implements Pheromone {

	public int age;
	public Position position;
	
	public PheromoneRecherche(Position position) {
		this.position = position;
		this.age = 0;
	}

	@Override
	public void step(Terrain terrain) {
		if (this.age >= 50) {
			List<Pheromone> list = terrain.getMapPheromones().get(this.position);
			list.remove(this);
			if (list.size() == 0) {
				terrain.getMapPheromones().remove(this.position);
			}
		} else {
			this.age++;
		}
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public boolean isReprodution() {
		return false;
	}
}
