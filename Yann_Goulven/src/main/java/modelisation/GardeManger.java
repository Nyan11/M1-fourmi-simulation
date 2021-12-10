package modelisation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modelisation.utils.Position;
import modelisation.utils.Simulable;
import modelisation.vivant.EtreVivant;
import modelisation.vivant.proie.Proie;

public class GardeManger implements Simulable {

	public Position position;
	public List<Proie> nourritures;
	public List<Proie> aRetirer;
	public List<Proie> dechets;

	public GardeManger(Position position) {
		int x = (int)(Math.random() * 10) - 5;
		int y = (int)(Math.random() * 10) - 5;
		this.position = new Position(position.x() - x, position.y() - y);
		this.nourritures = new ArrayList<Proie>();
		this.dechets = new ArrayList<Proie>();
		this.aRetirer = new ArrayList<Proie>();
	}

	@Override
	public void step(Terrain terrain) {
		updateNourriture(terrain);
		verificationRatio();
		nettoyerNourriture();
	}

	private void nettoyerNourriture() {
		this.nourritures.removeAll(this.aRetirer);
		this.aRetirer.clear();
	}

	private void verificationRatio() {
		if (this.nourritures.size() < this.dechets.size()) {
			// changer l'etat de la fourmiliere
			// du coup uine fourmiliere à des etats.
			// stable - crise - nettoyage
		}
	}

	private void updateNourriture(Terrain terrain) {
		for (Proie nourriture: this.nourritures) {
			nourriture.step(terrain);
		}
	}
	
	public Position getPosition() {
		return this.position;
	}

	public boolean possedeNourriture(float poids) {
		float qteManger = (float) 0.0;
		for (Proie proie : this.nourritures) {
			qteManger += proie.getPoids();
			if (qteManger > poids) {
				return true;
			}
		}
		return false;
	}

	public void retirerNourriture(float poids) {
		float qteManger = (float) 0.0;
		for (Proie proie : this.nourritures) {
			qteManger += proie.retirerPoids(poids, this);
			if (qteManger > poids) {
				return;
			}
		}
	}

	public void retirerNourriture(Proie proie) {
		this.aRetirer.add(proie);
	}

	public void ajouterNourriture(Proie proie) {
		this.nourritures.add(proie);
	}
	
	public float poidsTotalNourriture() {
		float qteManger = (float) 0.0;
		for (Proie proie : this.nourritures) {
			qteManger += proie.getPoids();
		}
		return qteManger;
	}

	public EtreVivant prendreAuGardeManger() {
		return this.nourritures.remove(0);
	}

	public boolean estVide() {
		return this.nourritures.size() == 0;
	}


}
