package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class ReineFonder implements Objectif {

	private boolean aCreerLaFourmiliere;
	
	public ReineFonder() {
		this.aCreerLaFourmiliere = false;
	}
	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return this.aCreerLaFourmiliere;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		return new ReinePondre();
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		terrain.creerFourmiliere(adulte);
		this.aCreerLaFourmiliere = true;
		System.out.println("ReineFonder.step - aCreerLaFourmiliere nbFourmilieres: " + terrain.getFourmilieres().size());
		System.out.println("ReineFonder.step - aCreerLaFourmiliere nbFourmis: " + terrain.getFourmis().size());
	}

}
