package modelisation.vivant.fourmi.etat;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;

public class Mort implements Stade {

	@Override
	public Stade grandir(int age, Fourmi fourmi, Terrain terrain) {
		if (age > 500) {
			terrain.supprimerFourmi(fourmi.getPosition(), fourmi);
		}
		return this;
	}

	@Override
	public void changerPopulation(Fourmiliere fourmiliere) {
		fourmiliere.nbAdultes--;
		fourmiliere.nbMorts++;
	}

	@Override
	public void step(Terrain terrain) {}

	@Override
	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain) {
		return this;
	}

	@Override
	public boolean epuiser() {
		return false;
	}

	@Override
	public EtreVivant manger(EtreVivant nourriture) {
		return nourriture;
	}

	@Override
	public boolean estMale() {
		return false;
	}

	@Override
	public boolean estFemelle() {
		return false;
	}

	@Override
	public boolean estReine() {
		return false;
	}

	@Override
	public boolean estSoldat() {
		return false;
	}

	@Override
	public boolean estMort() {
		return true;
	}

}
