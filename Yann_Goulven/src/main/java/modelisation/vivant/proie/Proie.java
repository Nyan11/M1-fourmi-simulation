package modelisation.vivant.proie;

import java.util.List;

import modelisation.GardeManger;
import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.fourmi.Fourmi;
import modelisation.vivant.fourmi.etat.Adulte;

public class Proie implements EtreVivant, Simulable {

	public float poids;
	public Position position;
	
	
	public Proie(float poids, Position position) {
		this.poids = poids;
		this.position = position;
	}

	@Override
	public void step(Terrain terrain) {}

	public Position getPosition() {
		return this.position;
	}

	public float getPoids() {
		return this.poids;
	}

	public float retirerPoids(float demander, GardeManger gardeManger) {
		if (this.poids < demander) {
			gardeManger.retirerNourriture(this);
			return this.poids;
		} else {
			this.poids = this.poids - demander;
			return demander;
		}
	}

	@Override
	public void ajouterAuGardeManger(GardeManger gardeManger) {
		gardeManger.ajouterNourriture(this);
	}

	@Override
	public boolean estNourriture() {
		return true;
	}

	@Override
	public void deplacer(Terrain terrain) {	}

	@Override
	public EtreVivant estManger(float f) {
		this.poids -= f;
		if (poids <= 0.0) {
			return null;
		} else {
			return this;
		}
	}

}
