package visualisation;

import java.awt.Color;
import java.awt.Dimension;

import modelisation.utils.Position;
import modelisation.vivant.fourmi.Fourmi;
import nicellipse.component.NiEllipse;
import nicellipse.component.NiRectangle;

public class BatimentVue {
private Color COULEUR = Color.WHITE;

	public NiRectangle vue;
	
	public BatimentVue(Position position, int type) {
		this.vue = new NiRectangle();
		this.vue.setSize(new Dimension(Simulation.ZOOM, Simulation.ZOOM));
		if (type == 0) COULEUR = Color.WHITE;
		if (type == 1) COULEUR = Color.LIGHT_GRAY;
		if (type == 2) COULEUR = Color.CYAN;
		this.vue.setBackground(COULEUR);
		this.vue.setLocation(
				400 + position.x() * Simulation.ZOOM,
				300 + position.y() * Simulation.ZOOM
			);
	}

}
