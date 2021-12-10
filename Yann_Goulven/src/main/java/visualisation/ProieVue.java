package visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import modelisation.pheromone.Pheromone;
import modelisation.utils.Position;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;
import nicellipse.component.NiEllipse;

public class ProieVue extends PositionVue {
	
	public ProieVue(Position position, List<EtreVivant> list) {
		super(position, 1);
		this.setColor(Color.GREEN);
	}
}
