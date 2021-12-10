package modelisation.vivant.fourmi.objectif;

import modelisation.Terrain;
import modelisation.vivant.fourmi.etat.Adulte;

public class NourrirDonnerNourriture implements Objectif {
	
	private Objectif objectifRetour;
	private boolean aNourri;

	public NourrirDonnerNourriture(Objectif objectifRetour) {
		this.objectifRetour = objectifRetour;
		this.aNourri = false;
	}

	@Override
	public boolean aValiderObjectif(Terrain terrain, Adulte adulte) {
		return aNourri;
	}

	@Override
	public Objectif objectifSuivant(Terrain terrain, Adulte adulte) {
		if (adulte.transporteNonNull()) {
			return new ChasseRamener(objectifRetour);
		} else {
			return objectifRetour;
		}
	}

	@Override
	public void step(Terrain terrain, Adulte adulte) {
		adulte.donnerAManger(terrain);
		this.aNourri = true;
	}

}
