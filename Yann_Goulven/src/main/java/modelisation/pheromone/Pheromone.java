package modelisation.pheromone;

import modelisation.Terrain;
import modelisation.utils.Position;

public interface Pheromone {
	
	public void step(Terrain terrain);

	public Position getPosition();

	public boolean isReprodution();

}
