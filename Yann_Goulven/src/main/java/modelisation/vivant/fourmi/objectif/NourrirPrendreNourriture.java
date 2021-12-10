package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.Comportement;
import modelisation.vivant.fourmi.etat.Adulte;

public class NourrirPrendreNourriture implements Objectif {

	private Objectif objectifRetour;

	public NourrirPrendreNourriture(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return adulte.transporteNourriture() || adulte.gardeMangerEstVide();
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (adulte.gardeMangerEstVide()) {
			return new ChasseRecherche(objectifRetour);
		} else {
			return new NourrirRejoindreNid(objectifRetour);
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementGardeManger(terrain);
		adulte.setProchainePosition(prochaine);
		if(adulte.presentSurLeGardeManger()) {
			adulte.prendreNourriture(terrain);
		}
	}
}
