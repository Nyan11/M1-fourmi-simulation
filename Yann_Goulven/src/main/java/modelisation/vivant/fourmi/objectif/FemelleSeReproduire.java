package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class FemelleSeReproduire implements Objectif {

	private FemelleFuir femelleFuir;
	private boolean reproduction;

	public FemelleSeReproduire(FemelleFuir femelleFuir) {
		this.femelleFuir = femelleFuir;
		this.reproduction = false;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return this.reproduction;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return femelleFuir;
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		int random = (int) (Math.random() * 300);
		if (random == 0) {
			adulte.devenirReine();
		}
		this.reproduction = true;
	}
}
