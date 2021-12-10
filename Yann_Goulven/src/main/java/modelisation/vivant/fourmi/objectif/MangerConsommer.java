package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class MangerConsommer implements Objectif {

	private boolean aManger;
	private Objectif objectifRetour;
	
	public MangerConsommer(Objectif objectifRetour) {
		this.aManger = false;
		this.objectifRetour = objectifRetour;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return true;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (this.aManger) {
			return objectifRetour;
		} else {
			return new ChasseRecherche(objectifRetour);
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		this.aManger = adulte.manger();
	}

}
