package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Fourmi;
import nicellipse.component.NiEllipse;

public class PheromoneVue extends PositionVue {
	
	CopyOnWriteArraySet<Pheromone> pheromones;
	
	public PheromoneVue(Position position, List<Pheromone> list) {
		super(position, 2);
		this.pheromones = new CopyOnWriteArraySet<Pheromone>(list);
		this.setColor(this.getColor(pheromones));
	}

	private Color getColor(CopyOnWriteArraySet<Pheromone> list) {
		Color color = Color.YELLOW;
		/*for (Pheromone pheromone: pheromones) {
			if (pheromone.isReprodution()) {
				return Color.MAGENTA;
			}
		}*/
		return color;
	}
}
