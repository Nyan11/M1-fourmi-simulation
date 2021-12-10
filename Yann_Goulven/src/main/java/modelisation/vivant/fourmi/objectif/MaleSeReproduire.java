package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class MaleSeReproduire implements Objectif {
	
	public boolean reproduction;
	
	public MaleSeReproduire() {
		this.reproduction = false;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return this.reproduction;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return null;
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		adulte.mourrir();
		this.reproduction = true;
	}

}
