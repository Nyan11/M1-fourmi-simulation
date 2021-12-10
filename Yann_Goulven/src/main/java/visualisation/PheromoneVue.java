package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;


public class PheromoneVue extends PositionVue {
	
	public PheromoneVue(Position position, List<Pheromone> list) {
		super(position, 2);
		this.setColor(this.getColor(list));
	}

	private Color getColor(List<Pheromone> list) {
		Color color = Color.YELLOW;
		for (Pheromone pheromone: list) {
			if (pheromone.isReprodution()) {
				return Color.MAGENTA;
			}
		}
		return color;
	}
}
