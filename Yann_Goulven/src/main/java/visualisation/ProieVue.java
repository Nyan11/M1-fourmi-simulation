package visualisation;

import java.awt.Color;
import java.awt.Dimension;

import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.proie.Proie;
import nicellipse.component.NiEllipse;

public class ProieVue {
	
	private final Color COULEUR = Color.GREEN;
	
	public EtreVivant proie;
	public NiEllipse vue;
	
	public ProieVue(EtreVivant proie) {
		this.proie = proie;
		this.vue = new NiEllipse();
		this.vue.setSize(new Dimension(Simulation.ZOOM, Simulation.ZOOM));
		this.vue.setBorderColor(COULEUR);
		this.vue.setBackground(COULEUR);
		this.update();
	}
	
	public void update() {
		this.vue.setLocation(
				400 + this.proie.getPosition().x() * Simulation.ZOOM,
				300 + this.proie.getPosition().y() * Simulation.ZOOM
			);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proie == null) ? 0 : proie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProieVue other = (ProieVue) obj;
		if (proie == null) {
			if (other.proie != null)
				return false;
		} else if (!proie.equals(other.proie))
			return false;
		return true;
	}
}
