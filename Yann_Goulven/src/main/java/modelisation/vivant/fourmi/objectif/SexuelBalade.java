package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.utils.Position;
import modelisation.vivant.fourmi.etat.Adulte;

public class SexuelBalade implements Objectif {

	private Objectif objectifRetour;
	private boolean decisionFaim;
	
	public SexuelBalade(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
		this.decisionFaim = false;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return this.aFaim(adulte.getTempsSansManger(), adulte.TEMPS_POUR_MANGER) || terrain.periodeDeReproduction();
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (this.decisionFaim) {
			this.decisionFaim = false;
			return new MangerRecherche(objectifRetour);
		} else if (terrain.periodeDeReproduction()) {
			return objectifRetour;
		} else {
			return this;
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		Position prochaine = adulte.getDeplacement().deplacementPatrouille(terrain);
		adulte.setProchainePosition(prochaine);
		adulte.lacherPheromone(terrain);
	}
	
	private boolean aFaim(int tempsSansManger, int tempsMax) {
		int random = (int)(Math.random() * 100);
		if (tempsSansManger < tempsMax / 2) {
			if (random < 3) {
				this.decisionFaim = true;
				return true;
			}
		} else if (tempsSansManger < tempsMax / 4) {
			if (random < 25) {
				this.decisionFaim = true;
				return true;
			}
		} else if (tempsSansManger < tempsMax / 10) {
			if (random < 80) {
				this.decisionFaim = true;
				return true;
			}
		}
		return false;
	}
}
