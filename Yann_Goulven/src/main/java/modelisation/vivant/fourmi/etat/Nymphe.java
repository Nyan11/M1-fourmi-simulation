package modelisation.vivant.fourmi.etat;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;

public class Nymphe implements Stade {

	@Override
	public Stade grandir(int age, Fourmi fourmi, Terrain terrain) {
		if (age > 50) {
			terrain.ajouteFourmi(fourmi);
			fourmi.getFourmiliere().retireFourmi(fourmi);
			return new Adulte(fourmi);
		} else {
			return this; 
		}
	}

	@Override
	public void changerPopulation(Fourmiliere fourmiliere) {
		fourmiliere.nbLarves--;
		fourmiliere.nbNymphes++;
	}

	@Override
	public void step(Terrain terrain) {	}

	@Override
	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain) {
		fourmiliere.nbNymphes--;
		fourmiliere.nbMorts++;
		return new Mort();
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
		return false;
	}
}
