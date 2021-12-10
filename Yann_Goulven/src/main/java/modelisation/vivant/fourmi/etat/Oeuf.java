package modelisation.vivant.fourmi.etat;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;

public class Oeuf implements Stade {

	@Override
	public Stade grandir(int age, Fourmi fourmi, Terrain terrain) {
		if (age > 30) {
			return new Larve();
		} else {
			return this; 
		}
	}

	@Override
	public void changerPopulation(Fourmiliere fourmiliere) {
		fourmiliere.nbOeufs++;
	}

	@Override
	public void step(Terrain terrain) { }

	@Override
	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain) {
		fourmiliere.nbOeufs--;
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
