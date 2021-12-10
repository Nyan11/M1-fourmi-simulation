package modelisation.vivant.fourmi.etat;

import modelisation.Fourmiliere;
import modelisation.Terrain;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;

public class Larve implements Stade {
	
	public final static int TEMPS_POUR_MANGER = 100;

	private float poids;
	private int tempsSansManger;
	
	public Larve() {
		this.poids = (float)(Math.random() * 3 + 5.0);
		this.tempsSansManger = TEMPS_POUR_MANGER;
	}
	
	@Override
	public Stade grandir(int age, Fourmi fourmi, Terrain terrain) {
		if (age > 120) {
			return new Nymphe();
		} else {
			return this; 
		}
	}

	@Override
	public void changerPopulation(Fourmiliere fourmiliere) {
		fourmiliere.nbOeufs--;
		fourmiliere.nbLarves++;
	}

	@Override
	public void step(Terrain terrain) { 
		this.tempsSansManger--;
	}

	@Override
	public Stade changerPopulationMort(Fourmiliere fourmiliere, Terrain terrain) {
		fourmiliere.nbLarves--;
		fourmiliere.nbMorts++;
		return new Mort();
	}

	@Override
	public boolean epuiser() {
		return this.tempsSansManger < 0;
	}

	@Override
	public EtreVivant manger(EtreVivant nourriture) {
		this.tempsSansManger = TEMPS_POUR_MANGER;
		return nourriture.estManger(this.poids / 3);
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
